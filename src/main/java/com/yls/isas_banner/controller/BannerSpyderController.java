package com.yls.isas_banner.controller;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.controller
 * @ClassName:SpyderController
 * @author :yice
 * @date:2021/2/2 15:03
 * @Description:
 */

import com.yls.isas_banner.Entity.Banner;
import com.yls.isas_banner.Entity.Classify;
import com.yls.isas_banner.dao.ExcelEntity;
import com.yls.isas_banner.Entity.Matches;
import com.yls.isas_banner.Entity.Title;
import com.yls.isas_banner.service.BannerDORepository;
import com.yls.isas_banner.pageProcessor.IsasBannerClassifyProcessor;
import com.yls.isas_banner.pageProcessor.IsasBannerPageProcessor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SpyderController <br/>
 * date: 2021/2/2 15:03 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@Controller
public class BannerSpyderController  implements ApplicationListener<ContextRefreshedEvent> {

    private Spider pageSpider;

    private Spider classifySpider;

    @Value("${isas.banner.page.num}")
    private int epochPageNum;

    @Value("${isas.banner.classify.start.url}")
    private String startUrl;

    @Value("${isas.banner.offsetcount}")
    private int offsetcount;
    @Resource
    private BannerDORepository bannerDORepository;
    @Resource
    private IsasBannerClassifyProcessor isasBannerClassifyProcessor;
    @Resource
    private IsasBannerPageProcessor isasBannerPageProcessor;


    private ExcelEntity excelEntity;
    private static AtomicBoolean isLast=new AtomicBoolean(true);
    private static AtomicInteger currentPageNum;
    public  Lock lock_main = new ReentrantLock();
    public  Lock lock_thread1 = new ReentrantLock();
    public  Lock lock_thread2 = new ReentrantLock();
    public Condition condition_main = lock_main.newCondition();
    public Condition condition_thread1 = lock_thread1.newCondition();
    public Condition condition_thread2 = lock_thread2.newCondition();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ArrayList<SpiderListener> spiderListeners = new ArrayList<>();
        spiderListeners.add(new SpiderListener() {
            @Override
            public void onSuccess(Request request) {
                System.out.println("Success");
            }

            @Override
            public void onError(Request request) {
                System.out.println("Error");
            }
        });
        classifySpider=Spider.create(isasBannerClassifyProcessor)

                //开启5个线程抓取
                .thread(1);
        pageSpider=Spider.create(isasBannerPageProcessor)
                .setSpiderListeners(spiderListeners)
                //开启5个线程抓取
                .thread(1);

    }

    public void  start(){
        System.out.println(isasBannerClassifyProcessor.getCookie());
        classifySpider.setExitWhenComplete(false);
        classifySpider.addUrl(startUrl);
        classifySpider.start();
        pageSpider.setExitWhenComplete(false);
        pageSpider.start();
    }

    public void save(Matches matches){
        int lastNum = matches.getTotal()%matches.getPageSize();
        int pageNum = 0;
        if (lastNum > 0) {
            pageNum = matches.getTotal()/matches.getPageSize()+1;
        }else {
            pageNum = matches.getTotal()/matches.getPageSize();
        }

        lock_thread1.lock();
        try {
            for (Banner banner : matches.getMatches()) {
                ExcelEntity aEntity = new ExcelEntity();
                aEntity.setEquipmentType(excelEntity.getEquipmentType());
                aEntity.setTitle_zh(excelEntity.getTitle_zh());
                aEntity.setTitle_en(excelEntity.getTitle_en());
                aEntity.setUrl(excelEntity.getUrl());
                aEntity.setIp(banner.getIp());
                if(!StringUtils.isEmpty(banner.getGeoInfo())) {
                    aEntity.setCountry(banner.getGeoInfo().getCountry().getNames().getZh_cn());
                    aEntity.setCity(banner.getGeoInfo().getCity().getNames().getZh_cn());
                }
                if(!StringUtils.isEmpty(banner.getPortInfo())){
                    aEntity.setService(banner.getPortInfo().getService());
                    aEntity.setPort(banner.getPortInfo().getPort());
                }
                aEntity.setBanner(banner.getRaw_data());
                if (banner.getProtocol() != null) {
                    aEntity.setTransProtocol(banner.getProtocol().getTransport());
                }else {
                    aEntity.setTransProtocol("tcp");
                }
                bannerDORepository.save(aEntity);
            }
            if(currentPageNum.get()==pageNum || currentPageNum.get()==epochPageNum){
                isLast.set(false);
            }else {
                currentPageNum.getAndIncrement();
            }

//            FileUtils.writeLines(new File("src/test/out/"+excelEntity.getEquipmentType()+".json"), excelEntitys);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            condition_thread1.signal();
            lock_thread1.unlock();
        }

    }


    public void processDetail(Classify classify){
        excelEntity= new ExcelEntity();
        List<Title> titles = classify.getTitles();
        excelEntity.setEquipmentType(classify.getClassify_en());
        lock_thread1.lock();
        try {
            int offsetNum =0;
            int count = 0;
            for (Title title:titles){
                if(offsetNum<offsetcount) {
                    offsetNum++;
                    ++count;
                    continue;
                }
                System.out.println(++count);
                String title_zh = title.getTitle_zh();
                String[] splits = title_zh.split(" ");
                excelEntity.setTitle_en(title.getTitle_en());
                excelEntity.setTitle_zh(title.getTitle_zh());
//                excelEntity.setEquipmentVendors(splits[0]);
//                if (splits.length<4){
//                    excelEntity.setEquipmentModel(splits[1]);
//                    excelEntity.setComponentName(splits[2]);
//                }
//                else if (splits.length==4){
//                    excelEntity.setEquipmentModel(splits[1]+" "+splits[2]);
//                    excelEntity.setComponentName(splits[3]);
//                }
                String url= String.format("https://www.zoomeye.org/search?q=%s",title.getFilter().replace(":","%3A").replaceAll("\"","%22"));
                excelEntity.setUrl(url);
                isasBannerPageProcessor.setReferer(url);
                currentPageNum = new AtomicInteger(1);
                isLast.set(true);
                while (isLast.get()){

                    String tagetUrl =url+"&p="+currentPageNum.get();;
                    pageSpider.addUrl(tagetUrl);
                    condition_thread1.await();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock_thread1.unlock();
        }
    }

    public static void writeExcel(List<HashMap<String ,String>> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);


                String c1= dataMap.getOrDefault("厂商","").toString();
                String c2= dataMap.getOrDefault("型号","").toString();
                String c3= dataMap.getOrDefault("组件名","").toString();
                String c4= dataMap.getOrDefault("设备类型","").toString();
                String c5= dataMap.getOrDefault("banner","").toString();
                String c6= dataMap.getOrDefault("country","").toString();
                String c7= dataMap.getOrDefault("city","").toString();
                String c8= dataMap.getOrDefault("ip","").toString();
                String c9= dataMap.getOrDefault("传输层协议","").toString();
                String c10= dataMap.getOrDefault("app","").toString();


                Cell cc1 = row.createCell(0);
                Cell cc2 = row.createCell(1);
                Cell cc3 = row.createCell(2);
                Cell cc4 = row.createCell(3);
                Cell cc5 = row.createCell(4);
                Cell cc6 = row.createCell(5);
                Cell cc7 = row.createCell(6);
                Cell cc8 = row.createCell(7);
                Cell cc9 = row.createCell(8);
                Cell cc10 = row.createCell(9);

                cc1.setCellValue(c1);
                cc2.setCellValue(c2);
                cc3.setCellValue(c3);
                cc4.setCellValue(c4);
                cc5.setCellValue(c5);
                cc6.setCellValue(c6);
                cc7.setCellValue(c7);
                cc8.setCellValue(c8);
                cc9.setCellValue(c9);
                cc10.setCellValue(c10);

            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith("xls")){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith("xlsx")){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}

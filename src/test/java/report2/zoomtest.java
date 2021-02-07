package report2;


import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @Author: chenxing.li
 * @Description:
 * @Date:Create：in 2021/1/30 11:32
 * @Modified By：
 */
public class zoomtest {
    @Test
    public void getjson(){
        List<HashMap<String ,String>> results=new ArrayList<>();

        Workbook wb =null;
        String filePath = "D:\\test1.xlsx";
        wb = readExcel(filePath);
        List<Map<String,String>> list = null;
        String cellData = null;
        //用来存放表中数据
        Sheet sheet = null;
        Row row = null;
        list = new ArrayList<Map<String,String>>();
        String columns[] = {"query","tag","厂商","型号","组件名"};
        //获取第一个sheet
        sheet = wb.getSheetAt(7);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        row = sheet.getRow(0);
        //获取最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 1; i<rownum; i++) {
            Map<String,String> map = new LinkedHashMap<String,String>();
            row = sheet.getRow(i);
            if(row !=null){
                for (int j=0;j<colnum;j++){
                    cellData = (String) getCellFormatValue(row.getCell(j));
                    map.put(columns[j], cellData);
                }
            }else{
                break;
            }
            list.add(map);
        }

    //遍历解析出来的list
        for (Map<String,String> map : list) {

            String queryJson = doGet("https://api.zoomeye.org/host/search?query="+"工控设备");
            JsonRootBean jsonRootBean = JSON.parseObject(queryJson,JsonRootBean.class);
            System.out.println(jsonRootBean.getMatches().size());
            for(Matches matches :jsonRootBean.getMatches()){
                try {
                    HashMap<String ,String> result=new HashMap<>();
                    result.put("厂商",map.getOrDefault("厂商",""));
                    result.put("型号",map.getOrDefault("型号",""));
                    result.put("组件名",map.getOrDefault("组件名",""));
                    result.put("设备类型","Gateway");
                    if(matches.getPortinfo().getBanner().length()>10000)
                        continue;
                    result.put("banner",matches.getPortinfo().getBanner());
                    result.put("country",matches.getGeoinfo().getCountry().getNames().getZh_cn());
                    result.put("city",matches.getGeoinfo().getCity().getNames().getZh_cn());
                    result.put("ip",matches.getIp());
                    result.put("传输层协议",matches.getProtocol().getTransport());
                    result.put("app",matches.getPortinfo().getService());
                    results.add(result);
                }catch (Exception ex){
                    System.out.println(matches.toString());
                }
            }

        }
        writeExcel(results,10,"D:\\final.xlsx");
       // doGet();
    }

    @Test
    public void testApi(){
        String queryJson = doGet("https://api.zoomeye.org/host/search?query="+"app%3A%22Sanyo%20PLC-XU300%20projector%20http%20config%22");
        JsonRootBean jsonRootBean = JSON.parseObject(queryJson,JsonRootBean.class);
        System.out.println(jsonRootBean.getMatches().size());
        for(Matches matches :jsonRootBean.getMatches()){
            try {
                HashMap<String ,String> result=new HashMap<>();
                result.put("厂商","厂商");
                result.put("型号","型号");
                result.put("组件名","组件名");
                result.put("设备类型","Gateway");
                if(matches.getPortinfo().getBanner().length()>10000)
                    continue;
                result.put("banner",matches.getPortinfo().getBanner());
                result.put("country",matches.getGeoinfo().getCountry().getNames().getZh_cn());
                result.put("city",matches.getGeoinfo().getCity().getNames().getZh_cn());
                result.put("ip",matches.getIp());
                result.put("传输层协议",matches.getProtocol().getTransport());
                result.put("app",matches.getPortinfo().getService());
            }catch (Exception ex){
                System.out.println(matches.toString());
            }
        }
    }

    public static void writeExcel(List<HashMap<String ,String>> dataList, int cloumnCount,String finalXlsxPath){
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
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式

                        cellValue = String.valueOf(cell.getNumericCellValue());

                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
    public  String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);

            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            connection.setRequestProperty("API-KEY","AAB574de-7560-1de1f-caca-9f535dddf1c");
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}

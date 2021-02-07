package com.yls.isas_banner.pageProcessor;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.pageProcessor
 * @ClassName:IsasBannerPageProcessor
 * @author :yice
 * @date:2021/2/2 10:12
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.yls.isas_banner.Entity.Classify;
import com.yls.isas_banner.Entity.Title;
import com.yls.isas_banner.controller.BannerSpyderController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: IsasBannerPageProcessor <br/>
 * date: 2021/2/2 10:12 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@Component
public class IsasBannerClassifyProcessor implements PageProcessor {

    @Resource
    private BannerSpyderController bannerSpyderController;

    private String referer;

    @Value("${isasBanner.cookie}")
    private String cookie;


    @Override
    public void process(Page page) {
        System.out.println("IsasBannerClassifyProcessor.process");
        Classify classify = JSONObject.parseObject(page.getRawText().substring(1,page.getRawText().length()-1), Classify.class);
        bannerSpyderController.processDetail(classify);


    }

    @Override
    public Site getSite() {
        System.out.println("cookie"+cookie);
        return Site.me()
                .setRetryTimes(3)
                .setSleepTime(5000)
                .setTimeOut(60000)
                .addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.146 Safari/537.36")
                .addHeader("cookie",cookie)
                .setCharset("UTF-8");
    }
    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public static void main(String[] args) {
        Spider.create(new IsasBannerClassifyProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.zoomeye.org/dork/titles?titles=[%22%E5%B7%A5%E6%8E%A7%E8%AE%BE%E5%A4%87__Industrial+Equipment%22]&start=0&end=77")
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
}

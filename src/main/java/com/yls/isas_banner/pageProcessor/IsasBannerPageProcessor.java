package com.yls.isas_banner.pageProcessor;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.pageProcessor
 * @ClassName:IsasBannerPageProcessor
 * @author :yice
 * @date:2021/2/2 11:31
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.yls.isas_banner.Entity.Classify;
import com.yls.isas_banner.Entity.Matches;
import com.yls.isas_banner.controller.BannerSpyderController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.lang.reflect.Member;

/**
 * ClassName: IsasBannerPageProcessor <br/>
 * date: 2021/2/2 11:31 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@Component
public class IsasBannerPageProcessor implements PageProcessor, SpiderListener {



    private String referer;
    @Value("${isasBanner.cookie}")
    private String cookie;
    @Value("${isasBanner.cube-authorization}")
    private String cubeAuthorization;

    public String getCubeAuthorization() {
        return cubeAuthorization;
    }

    public void setCubeAuthorization(String cubeAuthorization) {
        this.cubeAuthorization = cubeAuthorization;
    }

    @Resource
    private BannerSpyderController bannerSpyderController;

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

    @Override
    public void process(Page page) {
        System.out.println("IsasBannerPageProcessor.process");
        Matches matches = JSONObject.parseObject(page.getRawText(), Matches.class);
        bannerSpyderController.save(matches);
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setRetryTimes(3)
                .setSleepTime(3000)
                .setTimeOut(100000)
                .addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.146 Safari/537.36")
//                .addHeader("referer",referer)
//                .addHeader("cookie",cookie)
                .addHeader("cube-authorization",cubeAuthorization)
//                .addHeader("referer",referer)
                .addHeader("cookie",cookie)
                .setCharset("UTF-8");
    }

    public static void main(String[] args) {
        Spider.create(new IsasBannerPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.zoomeye.org/search?q=app%3A%22Digital%20Loggers%20Web%20Power%20Switch%22&p=2")
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }

    @Override
    public void onSuccess(Request request) {
        System.out.println("onSuccess");
    }

    @Override
    public void onError(Request request) {
        System.out.println("Error");
    }
}

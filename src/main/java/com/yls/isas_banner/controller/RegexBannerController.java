package com.yls.isas_banner.controller;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.controller
 * @ClassName:RegexBannerController
 * @author :yice
 * @date:2021/2/5 10:27
 * @Description:
 */

import com.yls.isas_banner.dao.BannerFiltered;
import com.yls.isas_banner.service.BannerFilterDORepository;
import com.yls.isas_banner.service.RegexBannerDORepository;
import com.yls.isas_banner.dao.IPCameraLinksys;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: RegexBannerController <br/>
 * date: 2021/2/5 10:27 <br/>
 *
 * @author yice <br/>
 * Description: <br/>
 */

@Component
public class RegexBannerController {


    @Resource
    private RegexBannerDORepository regexBannerDORepository;

    @Resource
    private BannerFilterDORepository bannerFilterDORepository;

    public void regexBanner() {
        int count = 0;
        int right = 0;
        try {
            List<BannerFiltered> entities = bannerFilterDORepository.findAll();
            for (BannerFiltered excelEntity : entities) {
                count++;
                if (excelEntity.getTitle_en().equalsIgnoreCase("Cisco WVC54GCA webcam rtspd")) {
                    String regex = "<title>(Linksys\\s([\\w\\d-]+)\\sWireless-G\\sInternet Video Camera)</title>";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(excelEntity.getBanner());
                    if (matcher.find()) {
                        IPCameraLinksys bannerFiltered = new IPCameraLinksys();
                        bannerFiltered.setBanner(excelEntity.getBanner());
                        bannerFiltered.setCity(excelEntity.getCity());
                        bannerFiltered.setCountry(excelEntity.getCountry());
                        bannerFiltered.setIp(excelEntity.getIp());
                        bannerFiltered.setPort(excelEntity.getPort());
                        bannerFiltered.setService(excelEntity.getService());
                        bannerFiltered.setEquipmentType(excelEntity.getEquipmentType());
                        bannerFiltered.setTransProtocol(excelEntity.getTransProtocol());
                        bannerFiltered.setUrl(excelEntity.getUrl());
                        bannerFiltered.setEquipmentVendors_en("Linksys");
                        bannerFiltered.setEquipmentVendors_ch("领势");
                        bannerFiltered.setRegex(regex);
                        bannerFiltered.setRegexType("response-http-title");
                        bannerFiltered.setComponentName("Internet Video Camera");
                        bannerFiltered.setEquipmentModel("Wireless-G");
                        regexBannerDORepository.save(bannerFiltered);
                        right++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(count);
        } finally {
            System.out.println(count);
            System.out.println(right);
        }

    }


}

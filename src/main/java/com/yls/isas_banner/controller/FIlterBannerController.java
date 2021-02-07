package com.yls.isas_banner.controller;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner
 * @ClassName:FIlterBannerController
 * @author :yice
 * @date:2021/2/4 17:31
 * @Description:
 */

import com.yls.isas_banner.dao.BannerFiltered;
import com.yls.isas_banner.dao.ExcelEntity;
import com.yls.isas_banner.service.BannerDORepository;
import com.yls.isas_banner.service.BannerFilterDORepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: FIlterBannerController <br/>
 * date: 2021/2/4 17:31 <br/>
 *
 * @author yice <br/>
 * Description: <br/>
 */
@Component
public class FIlterBannerController {

    @Resource
    private BannerDORepository bannerDORepository;

    @Resource
    private BannerFilterDORepository bannerFilterDORepository;

    public void filterBanner() {
        int count = 0;
        int right = 0;
        String services = "http https ftp ssh telnet";
        String titleex = "http https ftp config ssh telnet system device admin office ui text web";
        try {
            List<ExcelEntity> entities = bannerDORepository.findAll();
            for (ExcelEntity excelEntity : entities) {
                count++;
                String title_en = excelEntity.getTitle_en();
                String banner = excelEntity.getBanner();

                if (StringUtils.isBlank(excelEntity.getService()) || !services.contains(excelEntity.getService().toLowerCase())) {
                    continue;
                }
                if (StringUtils.isBlank(banner) || StringUtils.isBlank(title_en)) {
                    continue;
                }
                if (excelEntity.getTitle_en().equalsIgnoreCase("Linksys Compact Wireless-G Internet Video Camera httpd")) {
                    String regex = "<title>(Linksys\\s([\\w\\d-]+)\\sWireless-G\\sInternet Video Camera)</title>";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(excelEntity.getBanner());
                    if (matcher.find()) {
                        String[] title_split = title_en.split(" ");
                        for (int i = 0; i < title_split.length; i++) {
                            if (banner.toLowerCase().contains(title_split[i].toLowerCase())) {
                                if (!titleex.contains(title_split[i].toLowerCase())) {
                                    BannerFiltered bannerFiltered = new BannerFiltered();
                                    bannerFiltered.setBanner(excelEntity.getBanner());
                                    bannerFiltered.setCity(excelEntity.getCity());
                                    bannerFiltered.setCountry(excelEntity.getCountry());
                                    bannerFiltered.setIp(excelEntity.getIp());
                                    bannerFiltered.setPort(excelEntity.getPort());
                                    bannerFiltered.setService(excelEntity.getService());
                                    bannerFiltered.setEquipmentType(excelEntity.getEquipmentType());
                                    bannerFiltered.setTransProtocol(excelEntity.getTransProtocol());
                                    bannerFiltered.setTitle_en(excelEntity.getTitle_en());
                                    bannerFiltered.setTitle_zh(excelEntity.getTitle_zh());
                                    bannerFiltered.setUrl(excelEntity.getUrl());
                                    bannerFilterDORepository.save(bannerFiltered);
                                    break;
                                }
                            }
                        }
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

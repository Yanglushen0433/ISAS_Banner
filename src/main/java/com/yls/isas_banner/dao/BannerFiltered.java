package com.yls.isas_banner.dao;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:BannerFiltered
 * @author :yice
 * @date:2021/2/4 16:59
 * @Description:
 */

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ClassName: BannerFiltered <br/>
 * date: 2021/2/4 16:59 <br/>
 * @author yice <br/>
 * Description: <br/>
 */

@Data
@Document(collection = "banner")
public class BannerFiltered {

    private static final long serialVersionUID = -32L;
    //ip
    private String ip;
    //端口号
    private int port;
    //传输层协议
    private String transProtocol;
    //应用层协议
    private String Service;
    //组件名
    private String componentName;
    //设备类型
    private String equipmentType;
    //设备厂商
    private String equipmentVendors;
    //设备/软件型号
    private String equipmentModel;
    //涉及领域
    private String domain;
    //国家
    private String country;
    //省份
    private String province;
    //地区
    private String city;
    //更新时间
    private String updateTime;
    //指纹
    private String banner;

    private String title_en;

    private String title_zh;

    private String url;
}

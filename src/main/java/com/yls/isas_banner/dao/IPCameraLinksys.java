package com.yls.isas_banner.dao;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:IPCameraLinksys
 * @author :yice
 * @date:2021/2/5 10:23
 * @Description:
 */

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ClassName: IPCameraLinksys <br/>
 * date: 2021/2/5 10:23 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@Data
@Document(collection = "Linksys")
public class IPCameraLinksys {
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
    private String equipmentVendors_en;
    //设备厂商
    private String equipmentVendors_ch;
    //设备/软件型号
    private String equipmentModel;
    //国家
    private String country;
    //地区
    private String city;
    //更新时间
    private String updateTime;
    //指纹
    private String banner;

    private String url;
    //正则表达式
    private String regex;
    //匹配类型(http)
    private String regexType;
}

package com.yls.isas_banner.dao;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:ExcelEntity
 * @author :yice
 * @date:2021/2/2 14:41
 * @Description:
 */

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * ClassName: ExcelEntity <br/>
 * date: 2021/2/2 14:41 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@Data
@Document(collection = "Router%")
public class ExcelEntity implements Serializable {

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

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_zh() {
        return title_zh;
    }

    public void setTitle_zh(String title_zh) {
        this.title_zh = title_zh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTransProtocol() {
        return transProtocol;
    }

    public void setTransProtocol(String transProtocol) {
        this.transProtocol = transProtocol;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentVendors() {
        return equipmentVendors;
    }

    public void setEquipmentVendors(String equipmentVendors) {
        this.equipmentVendors = equipmentVendors;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}

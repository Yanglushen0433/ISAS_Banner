package com.yls.isas_banner.Entity;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:Banner
 * @author :yice
 * @date:2021/2/2 11:49
 * @Description:
 */

/**
 * ClassName: Banner <br/>
 * date: 2021/2/2 11:49 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public class Banner {
    private String ip;
    private GeoInfo geoInfo;
    private PortInfo portInfo;
    private String raw_data;
    private Protocol protocol;

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoInfo geoInfo) {
        this.geoInfo = geoInfo;
    }

    public PortInfo getPortInfo() {
        return portInfo;
    }

    public void setPortInfo(PortInfo portInfo) {
        this.portInfo = portInfo;
    }

    public String getRaw_data() {
        return raw_data;
    }

    public void setRaw_data(String raw_data) {
        this.raw_data = raw_data;
    }
}

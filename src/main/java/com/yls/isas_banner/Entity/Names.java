/**
  * Copyright 2021 jb51.net 
  */
package com.yls.isas_banner.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2021-01-30 14:30:50
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Names {
    private String en;

    public String getZh_cn() {
        return zh_cn;
    }

    public void setZh_cn(String zh_cn) {
        this.zh_cn = zh_cn;
    }

    @JsonProperty("zh-CN")
    private String zh_cn;



    public void setEn(String en) {
         this.en = en;
     }
     public String getEn() {
         return en;
     }

}
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
public class City {

    @JsonProperty("geoname_id")
    private String geonameId;
    private Names names;
    public void setGeonameId(String geonameId) {
         this.geonameId = geonameId;
     }
     public String getGeonameId() {
         return geonameId;
     }

    public void setNames(Names names) {
         this.names = names;
     }
     public Names getNames() {
         return names;
     }

}
/**
  * Copyright 2021 jb51.net 
  */
package report2;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2021-01-30 14:30:50
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Subdivisions {

    @JsonProperty("geoname_id")
    private String geonameId;
    private String code;
    private Names names;
    public void setGeonameId(String geonameId) {
         this.geonameId = geonameId;
     }
     public String getGeonameId() {
         return geonameId;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setNames(Names names) {
         this.names = names;
     }
     public Names getNames() {
         return names;
     }

}
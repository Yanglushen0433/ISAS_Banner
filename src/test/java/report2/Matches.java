/**
  * Copyright 2021 jb51.net 
  */
package report2;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2021-01-30 14:30:50
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Matches {

    private Date timestamp;
    private String ip;
    private Geoinfo geoinfo;
    private Portinfo portinfo;
    private Protocol protocol;
    @JsonProperty("rdns_new")
    private String rdnsNew;
    private int honeypot;
    public void setTimestamp(Date timestamp) {
         this.timestamp = timestamp;
     }
     public Date getTimestamp() {
         return timestamp;
     }

    public void setIp(String ip) {
         this.ip = ip;
     }
     public String getIp() {
         return ip;
     }

    public void setGeoinfo(Geoinfo geoinfo) {
         this.geoinfo = geoinfo;
     }
     public Geoinfo getGeoinfo() {
         return geoinfo;
     }

    public void setPortinfo(Portinfo portinfo) {
         this.portinfo = portinfo;
     }
     public Portinfo getPortinfo() {
         return portinfo;
     }

    public void setProtocol(Protocol protocol) {
         this.protocol = protocol;
     }
     public Protocol getProtocol() {
         return protocol;
     }

    public void setRdnsNew(String rdnsNew) {
         this.rdnsNew = rdnsNew;
     }
     public String getRdnsNew() {
         return rdnsNew;
     }

    public void setHoneypot(int honeypot) {
         this.honeypot = honeypot;
     }
     public int getHoneypot() {
         return honeypot;
     }

}
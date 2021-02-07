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
public class GeoInfo {

    private City city;
    private Country country;
    private String isp;
    private Continent continent;
    private Subdivisions subdivisions;
    private Location location;
    private String organization;
    private String aso;
    private String asn;
    @JsonProperty("base_station")
    private String baseStation;
    private String idc;
    @JsonProperty("PoweredBy")
    private String poweredby;
    @JsonProperty("organization_CN")
    private String organizationCn;
    public void setCity(City city) {
         this.city = city;
     }
     public City getCity() {
         return city;
     }

    public void setCountry(Country country) {
         this.country = country;
     }
     public Country getCountry() {
         return country;
     }

    public void setIsp(String isp) {
         this.isp = isp;
     }
     public String getIsp() {
         return isp;
     }

    public void setContinent(Continent continent) {
         this.continent = continent;
     }
     public Continent getContinent() {
         return continent;
     }

    public void setSubdivisions(Subdivisions subdivisions) {
         this.subdivisions = subdivisions;
     }
     public Subdivisions getSubdivisions() {
         return subdivisions;
     }

    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setOrganization(String organization) {
         this.organization = organization;
     }
     public String getOrganization() {
         return organization;
     }

    public void setAso(String aso) {
         this.aso = aso;
     }
     public String getAso() {
         return aso;
     }

    public void setAsn(String asn) {
         this.asn = asn;
     }
     public String getAsn() {
         return asn;
     }

    public void setBaseStation(String baseStation) {
         this.baseStation = baseStation;
     }
     public String getBaseStation() {
         return baseStation;
     }

    public void setIdc(String idc) {
         this.idc = idc;
     }
     public String getIdc() {
         return idc;
     }

    public void setPoweredby(String poweredby) {
         this.poweredby = poweredby;
     }
     public String getPoweredby() {
         return poweredby;
     }

    public void setOrganizationCn(String organizationCn) {
         this.organizationCn = organizationCn;
     }
     public String getOrganizationCn() {
         return organizationCn;
     }

}
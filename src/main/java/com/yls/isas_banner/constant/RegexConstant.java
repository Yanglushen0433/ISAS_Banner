package com.yls.isas_banner.constant;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.constant
 * @ClassName:RegexConstant
 * @author :yice
 * @date:2021/2/5 9:30
 * @Description:
 */

/**
 * ClassName: RegexConstant <br/>
 * date: 2021/2/5 9:30 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public  class RegexConstant {
    private static final String HTTPRESPONSEHEADER="^(HTTP/1.0 .+[\\f\\n\\r\\t\\v])([A-Za-z-]+:[^<]+[\\f\\n\\r\\t\\v])+";

    private static final String LinsysWireless_GInternetVideoCamera ="<title>(Linksys\\s([\\w\\d-]+)\\sWireless-G\\sInternet Video Camera)</title>";
}

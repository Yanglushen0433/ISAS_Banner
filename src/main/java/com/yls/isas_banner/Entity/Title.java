package com.yls.isas_banner.Entity;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:Title
 * @author :yice
 * @date:2021/2/2 11:18
 * @Description:
 */

/**
 * ClassName: Title <br/>
 * date: 2021/2/2 11:18 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public class Title {
    private String filter;
    private String title;
    private String title_zh;
    private String title_en;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_zh() {
        return title_zh;
    }

    public void setTitle_zh(String title_zh) {
        this.title_zh = title_zh;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }
}

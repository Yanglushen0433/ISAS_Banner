package com.yls.isas_banner.Entity;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:Classify
 * @author :yice
 * @date:2021/2/2 11:16
 * @Description:
 */

import java.util.List;

/**
 * ClassName: Classify <br/>
 * date: 2021/2/2 11:16 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public class Classify {
    private String classify_zh;
    private String classify_en;
    private List<Title> titles;

    public String getClassify_zh() {
        return classify_zh;
    }

    public void setClassify_zh(String classify_zh) {
        this.classify_zh = classify_zh;
    }

    public String getClassify_en() {
        return classify_en;
    }

    public void setClassify_en(String classify_en) {
        this.classify_en = classify_en;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }
}

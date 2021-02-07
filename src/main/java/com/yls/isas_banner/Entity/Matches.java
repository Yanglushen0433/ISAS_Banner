package com.yls.isas_banner.Entity;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.Entity
 * @ClassName:Banner
 * @author :yice
 * @date:2021/2/2 11:47
 * @Description:
 */


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ClassName: Banner <br/>
 * date: 2021/2/2 11:47 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public class Matches {
    private List<Banner> matches;
    private int total;
    private int pageSize;

    public List<Banner> getMatches() {
        return matches;
    }

    public void setMatches(List<Banner> matches) {
        this.matches = matches;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

package com.yls.isas_banner;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner
 * @ClassName:IsasBanner
 * @author :yice
 * @date:2021/2/2 9:30
 * @Description:
 */

import com.yls.isas_banner.controller.BannerSpyderController;
import com.yls.isas_banner.controller.FIlterBannerController;
import com.yls.isas_banner.controller.RegexBannerController;
import com.yls.isas_banner.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: IsasBanner <br/>
 * date: 2021/2/2 9:30 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
@SpringBootApplication
public class IsasBanner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(IsasBanner.class);
        app.run(args);
//        SpringContextUtils.getBean(FIlterBannerController.class).filterBanner();
//        SpringContextUtils.getBean(RegexBannerController.class).regexBanner();
        SpringContextUtils.getBean(BannerSpyderController.class).start();
    }
}

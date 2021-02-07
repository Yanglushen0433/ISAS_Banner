package com.yls.isas_banner.service;

import com.yls.isas_banner.dao.IPCameraLinksys;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @author :yice
 * @PackageName: com.yls.isas_banner.dao
 * @ClassName:RegexBannerDORepository
 * @date:2021/2/5 10:29
 * @Description:
 */
public interface RegexBannerDORepository  extends MongoRepository<IPCameraLinksys,String> {
}

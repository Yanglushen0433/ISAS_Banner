package com.yls.isas_banner.service;/**
 * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 *
 * @PackageName: com.yls.isas_banner.dao
 * @ClassName:BannerDORepository
 * @author :yice
 * @date:2021/2/3 11:41
 * @Description:
 */

import com.yls.isas_banner.dao.ExcelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ClassName: BannerDORepository <br/>
 * date: 2021/2/3 11:41 <br/>
 * @author yice <br/>
 * Description: <br/>
 */
public interface BannerDORepository extends MongoRepository<ExcelEntity,String> {
}

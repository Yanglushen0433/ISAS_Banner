//package report2;
//
//import com.config.BaseConfig;
//import com.winicssec.soc.configuration.ProfileConfig;
//import com.winicssec.soc.core.common.entity.db.Criteria;
//import com.winicssec.soc.core.common.helper.ElasticSearchHelper;
//import com.winicssec.soc.core.common.task.ProtectStatisticsJob;
//import com.winicssec.soc.core.common.task.ReportStatsJob;
//import com.winicssec.soc.core.report.constant.ReportConstant;
//import com.winicssec.soc.core.report.constant.ReportFactoryInfoConstant;
//import com.winicssec.soc.core.report.constant.ReportManageField;
//import com.winicssec.soc.core.report.constant.SafeSituationConstant;
//import com.winicssec.soc.core.report.dao.ReportManageDao;
//import com.winicssec.soc.core.report.entity.ReportManageDO;
//import com.winicssec.soc.core.report.entity.ReportResultVO;
//import com.winicssec.soc.core.report.helper.TemplateHelper;
//import com.winicssec.soc.core.report.statistics.impl.AssetTypeStats;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.quartz.JobExecutionException;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.time.LocalDate;
//import java.util.List;
//
//
///**
// * Copyright © 2020 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
// *
// * @Prject: soc
// * @Package: com.report
// * @Description:
// * @author: chengxuebo
// * @date: 2020/2/27 18:02
// * @version: V1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ProfileConfig.class, BaseConfig.class})
//@ActiveProfiles("auto")
//@WebAppConfiguration()
//public class ReportTest {
//
//    @Resource
//    private ReportStatsJob reportStatsJob;
//
//    @Resource
//    private TemplateHelper templateHelper;
//
//    @Resource
//    private ReportManageDao reportManageDao;
//
//    @Resource
//    private AssetTypeStats assetTypeStats;
//
//    @Resource
//    private ElasticSearchHelper elasticSearchHelper;
//
//    @Resource
//    private ProtectStatisticsJob protectStatisticsJob;
//
//    @Test
//    public void delete(){
//        elasticSearchHelper.deleteDataByQuery(QueryBuilders.boolQuery(), ReportFactoryInfoConstant.INDEX);
////        elasticSearchHelper.deleteDataByQuery(QueryBuilders.boolQuery(), SafeSituationConstant.INDEX);
//    }
//
//    @Test
//    public void test() throws JobExecutionException {
//        reportStatsJob.execute(null);
////        protectStatisticsJob.execute(null);
//        System.out.println("end");
//    }
//
//    @Test
//    public void download() throws Exception {
//        Criteria criteria = Criteria.create().andEq(ReportManageField.FIELD_ID, 11);
//        ReportManageDO report = reportManageDao.findByCriteria(null, criteria);
//        templateHelper.download(report,new FileOutputStream(new File("E:\\生产监控\\002.Code\\code\\soc-web\\src\\test\\resources\\result\\result.docx")));
//        System.out.println("end");
//    }
//
//    @Test
//    public void pictureTest(){
//        Integer factoryId = 2;
//        LocalDate endTime = LocalDate.now().minusDays(2);
//        List<ReportResultVO> reportResult = assetTypeStats.getReportResult(factoryId, endTime, SafeSituationConstant.TOP_TEN,null);
//        assetTypeStats.getBarPicture(reportResult, 19+ReportConstant.ASSET_TYPE_IMAGE);
//    }
//
//    @Test
//    public void deleteTest(){
//        elasticSearchHelper.deleteDataByQuery(QueryBuilders.boolQuery(), "soc_event_security");
//        System.out.println("end");
//    }
//
//}

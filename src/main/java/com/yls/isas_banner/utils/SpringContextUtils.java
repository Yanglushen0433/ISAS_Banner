/**   
 * Copyright © 2019 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 * 
 * @Title: SpringContextUtil.java 
 * @Prject: spider-vulnerability-cnnvd
 * @Package: com.winicssec.spider.vulnerability.cnnvd.util 
 * @Description: TODO
 * @author: Biao.Huang   
 * @date: 2019年12月13日 上午11:16:04 
 * @version: V2.0   
 */
package com.yls.isas_banner.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**   
 * Copyright © 2019 Beijing WINICSSEC Technologies Co.,Ltd. All rights reserved.
 * 
 * @Title: SpringContextUtils.java 
 * @Prject: spider-vulnerability-cnnvd
 * @Package: com.winicssec.spider.vulnerability.cnnvd.util 
 * @Description: spring上下文工具类
 * @author: Biao.Huang 
 * @date: 2019年12月13日 上午11:16:04 
 * @version: V2.0   
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 
     * @Title: getApplicationContext 
     * @Description: 获取applicationContext
     * @return
     * @return: ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 
     * @Title: getBean 
     * @Description: 通过name获取 Bean.
     * @param name
     * @return
     * @return: Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 
     * @Title: getBean 
     * @Description: 通过class获取Bean.
     * @param clazz
     * @return
     * @return: T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 
     * @Title: getBean 
     * @Description: 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @return
     * @return: T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}

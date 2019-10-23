package com.mgtv.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 16:19
 */
@EnableMethodCache(basePackages = "com.mgtv.demo")
@EnableTransactionManagement
@EnableCreateCacheAnnotation
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DemoApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DemoApplication.class, args);
    }
}

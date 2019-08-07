package com.mgtv.demo;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-05 16:19
 */
@EnableMethodCache(basePackages = "com.mgtv.demo")
@EnableCreateCacheAnnotation
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DemoApplication.class, args);
    }
}

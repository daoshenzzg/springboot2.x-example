package com.mgtv.demo.config.redis;

import com.alicp.jetcache.autoconfigure.LettuceFactory;
import com.alicp.jetcache.autoconfigure.RedisLettuceAutoConfiguration;
import io.lettuce.core.cluster.RedisClusterClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-15 16:06
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean(name = "defaultClient")
    @DependsOn(RedisLettuceAutoConfiguration.AUTO_INIT_BEAN_NAME)
    public LettuceFactory defaultClient() {

        return new LettuceFactory("remote.default", RedisClusterClient.class);
    }

    /**
     * 解决 Idea "Could not autowire" 告警。
     *
     * @return
     */
    @Bean
    public RedisClusterClient redisClusterClient() {
        return null;
    }

}
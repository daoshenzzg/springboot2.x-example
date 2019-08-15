package com.mgtv.demo.config.redis;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis客户端
 *
 * @author zhiguang@mgtv.com
 * @date 2019-08-15 16:09
 */
@Component
public class RedisClient {

    private static final ReentrantLock lock = new ReentrantLock();

    private StatefulRedisClusterConnection<String, String> defaultConn;

    @Autowired
    private RedisClusterClient defaultClient;

    public StatefulRedisClusterConnection<String, String> connect() {
        if (defaultConn == null) {
            lock.lock();
            try {
                if (defaultConn == null) {
                    defaultConn = defaultClient.connect();
                }
            } finally {
                lock.unlock();
            }
        }
        return defaultConn;
    }
}

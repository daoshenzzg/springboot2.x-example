package com.mgtv.demo.mq.rocketmq;

import com.mgtv.demo.pojo.dto.DemoMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-07 15:19
 */
@Slf4j
@Service
@RocketMQMessageListener(
        topic = "${rocketmq.demo.topic}",
        consumerGroup = "CID_demo-d")
public class DemoConsumer implements RocketMQListener<DemoMessageDTO> {
    @Override
    public void onMessage(DemoMessageDTO demoMessage) {
        log.info("received message: {}", demoMessage);
    }
}

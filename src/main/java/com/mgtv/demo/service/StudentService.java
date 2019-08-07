package com.mgtv.demo.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.mgtv.demo.dao.es.DemoRepository;
import com.mgtv.demo.dao.mapper.master.StudentMapper;
import com.mgtv.demo.pojo.dto.DemoMessageDTO;
import com.mgtv.demo.pojo.document.DemoDocument;
import com.mgtv.demo.pojo.model.master.StudentDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-05 18:42
 */
@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private DemoRepository demoRepository;

    /**
     * 学生列表
     *
     * @return
     */
    public List<StudentDO> listStudent() {
        return studentMapper.listStudent();
    }

    /**
     * 获取学生 redis缓存
     *
     * @param id
     * @return
     */
    @Cached(name = "student:", key = "#id", cacheType = CacheType.REMOTE, expire = 600)
    public StudentDO getStudent(long id) {
        return studentMapper.getStudent(id);
    }

    /**
     * 获取学生 本地缓存
     *
     * @param id
     * @return
     */
    @Cached(name = "student:", key = "#id", cacheType = CacheType.LOCAL, expire = 600)
    public StudentDO getStudentLocal(long id) {
        return studentMapper.getStudent(id);
    }

    /**
     * 发送MQ消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        DemoMessageDTO demoMessage = new DemoMessageDTO();
        demoMessage.setId(1L);
        demoMessage.setMessage(message);
        SendResult sendResult = rocketMQTemplate.syncSend("demo-topic", demoMessage);
        log.info("---------sendMessage sendResult: {}", sendResult);
    }

    /**
     * 保存Doc
     *
     * @param doc
     */
    public void saveDoc(DemoDocument doc) {
        demoRepository.save(doc);
        log.info("--------saveDoc success: {}", doc);
    }

    /**
     * 查询Doc
     *
     * @param id
     * @return
     */
    public DemoDocument getDoc(String id) {
        return demoRepository.findById(id).get();
    }
}

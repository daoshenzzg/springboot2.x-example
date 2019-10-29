package com.yb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.demo.common.enums.CodeEnum;
import com.yb.demo.common.exception.BizException;
import com.yb.demo.config.redis.RedisClient;
import com.yb.demo.dao.es.DemoRepository;
import com.yb.demo.dao.mapper.db1.Student1Mapper;
import com.yb.demo.dao.mapper.db2.Student2Mapper;
import com.yb.demo.pojo.document.DemoDocument;
import com.yb.demo.pojo.dto.DemoMessageDTO;
import com.yb.demo.pojo.model.db1.Student1DO;
import com.yb.demo.pojo.model.db2.Student2DO;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 18:42
 */
@Slf4j
@Service
public class StudentService extends ServiceImpl<Student1Mapper, Student1DO> {

    @Resource
    private Student1Mapper student1Mapper;

    @Resource
    private Student2Mapper student2Mapper;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private RedisClient redisClient;

    @Resource(name = "httpClientTemplate")
    private RestTemplate restTemplate;

    /**
     * 学生列表
     *
     * @return
     */
    public List<Student1DO> listStudent(String studName) {
        QueryWrapper<Student1DO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("stud_name", studName);
        return super.list(queryWrapper);
    }

    /**
     * 学生列表
     *
     * @return
     */
    public IPage<Student1DO> listStudentByPage(String studName, int pageNum, int pageSize) {
        Page<Student1DO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student1DO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name", studName);
        return super.page(page, queryWrapper);
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    public Student1DO addStudent(Student1DO student) {
        if (StringUtils.isBlank(student.getStudName())) {
            // 举例扔个业务异常，实际使用过程中，应该避免扔异常
            throw new BizException(CodeEnum.REQUEST_ERR).withRemark("studName不能为空");
        }
        student1Mapper.insert(student);
        return student;
    }


    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public IPage<Student1DO> listStudentPage(int pageNo, int pageSize) {
        Page<Student1DO> page = new Page<>(pageNo, pageSize);
        return student1Mapper.listStudentPage(page);
    }

    /**
     * 获取学生 redis缓存
     *
     * @param id
     * @return
     */
    @Cached(name = "student:", key = "#id", cacheType = CacheType.REMOTE, expire = 600)
    public Student1DO getStudent(long id) {
        return student1Mapper.selectById(id);
    }

    /**
     * 获取学生 本地缓存
     *
     * @param id
     * @return
     */
    @Cached(name = "student:", key = "#id", cacheType = CacheType.LOCAL, expire = 600)
    public Student1DO getStudentLocal(long id) {
        return student1Mapper.selectById(id);
    }

    @DS("db1-master")
    public Student1DO getStudentDb1Master(long id) {
        return student1Mapper.selectById(id);
    }

    @DS("db1-slave")
    public Student1DO getStudentDb1Slave(long id) {
        return student1Mapper.selectById(id);
    }

    @DS("db2-slave")
    public Student2DO getStudentDB2Slave(long id) {
        return student2Mapper.selectById(id);
    }

    /**
     * 只能在同一数据源下使用事务
     *
     * @param student
     */
    @DS("db1-master")
    @Transactional(rollbackFor = Exception.class)
    public void txSave(Student1DO student) {
        // 新增
        student1Mapper.insert(student);
        // 查询
        Student1DO student1 = student1Mapper.selectById(1);
        // 修改
        student1.setStudName(student1.getStudName() + "-修改");
        student1Mapper.updateById(student1);
    }

    /**
     * Redis List操作
     * <p>
     * 画外音：其实只要获取到RedisAdvancedClusterCommands对象，就可以操作redis的全部命令，用法跟Jedis一样
     */
    public String testRdisList() {
        RedisAdvancedClusterCommands<String, String> redisCluster = redisClient.connect().sync();
        redisCluster.lpush("test_list", "test redis list!");
        String value = redisCluster.rpop("test_list");
        log.info("---------redisCluster.rpop: {}", value);
        return value;
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

    /**
     * HTTP get
     *
     * @return
     */
    public String httpGet() {
        String url = "http://fantuan.bz.mgtv.com/fantuan/soKeyword";
        String result = restTemplate.getForObject(url, String.class);
        JSONObject jsonData = JSON.parseObject(result);
        return jsonData.getJSONObject("data").getString("keyword");
    }
}

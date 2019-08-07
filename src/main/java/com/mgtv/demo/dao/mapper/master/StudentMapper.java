package com.mgtv.demo.dao.mapper.master;

import com.mgtv.demo.pojo.model.master.StudentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-05 17:56
 */
@Mapper
public interface StudentMapper {

    /**
     * 学生列表
     *
     * @return
     */
    List<StudentDO> listStudent();

    /**
     * 获取学生
     *
     * @param id
     * @return
     */
    StudentDO getStudent(long id);
}

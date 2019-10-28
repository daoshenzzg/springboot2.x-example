package com.yb.demo.dao.mapper.db1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yb.demo.pojo.model.db1.Student1DO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 17:56
 */

@Repository
public interface Student1Mapper extends BaseMapper<Student1DO> {
    /**
     * 分页查询
     * <p>
     * page 参数必须放第一个，后面可以加自定义参数，在xml获取
     *
     * @param page
     * @return
     */
    IPage<Student1DO> listStudentPage(Page page);

    /**
     * 模糊查询
     *
     * @param studName
     * @return
     */
    List<Student1DO> selectByName(@Param("studName") String studName);
}

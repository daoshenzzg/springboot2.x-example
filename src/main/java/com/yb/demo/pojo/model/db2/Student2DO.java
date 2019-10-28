package com.yb.demo.pojo.model.db2;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 17:58
 */
@Data
@TableName("student")
public class Student2DO {
    private Long id;
    private String studName;
    private Integer studAge;
    private String studSex;
    private Integer createTime;
    private Integer updateTime;
}

package com.mgtv.demo.pojo.model.db1;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 17:58
 */
@Data
@TableName("student")
public class Student1DO {
    private Long id;
    private String studName;
    private Integer studAge;
    private String studSex;
    @TableField(fill = FieldFill.INSERT)
    private Integer createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;
}

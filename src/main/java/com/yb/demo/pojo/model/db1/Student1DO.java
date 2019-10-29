package com.yb.demo.pojo.model.db1;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 17:58
 */
@Data
@TableName("student")
public class Student1DO {
    private Long id;
    @Size(max = 8, message = "studName长度不能超过8")
    private String studName;
    @Min(value = 12, message = "年龄不能低于12岁")
    private Integer studAge;
    private String studSex;
    @TableField(fill = FieldFill.INSERT)
    private Integer createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;
}

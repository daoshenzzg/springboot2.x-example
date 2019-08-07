package com.mgtv.demo.pojo.model.master;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-05 17:58
 */
@Data
public class StudentDO implements Serializable {
    private Long id;
    private String studName;
    private Integer studAge;
    private String studSex;
    private Integer createTime;
    private Integer updateTime;
}

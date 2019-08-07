package com.mgtv.demo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-06 17:39
 */
@Data
public class DemoMessageDTO implements Serializable {
    private Long id;
    private String message;
}

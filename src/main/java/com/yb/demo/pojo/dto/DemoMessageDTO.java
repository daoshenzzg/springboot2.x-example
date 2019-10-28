package com.yb.demo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-06 17:39
 */
@Data
public class DemoMessageDTO implements Serializable {
    private Long id;
    private String message;
}

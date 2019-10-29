package com.yb.demo.pojo.response;

import com.yb.demo.pojo.model.db1.Student1DO;
import lombok.Data;

/**
 * @author daoshenzzg@163.com
 * @date 2019-10-29 11:07
 */
@Data
public class StudentVO extends Student1DO {
    private String teacherName;
}

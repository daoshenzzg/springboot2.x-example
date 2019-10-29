package com.yb.demo.common.exception;

import com.yb.demo.common.enums.CodeEnum;
import lombok.Data;

/**
 * 业务异常跳转。
 *
 * @author daoshenzzg@163.com
 * @date 2019-09-09 14:57
 */
@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeEnum code;
    private String remark;

    public BizException(CodeEnum code) {
        super(code.getMessage());
        this.code = code;
    }

    public BizException withRemark(String remark) {
        this.remark = remark;
        return this;
    }
}

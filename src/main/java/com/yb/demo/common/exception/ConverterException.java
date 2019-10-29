package com.yb.demo.common.exception;

/**
 * 对象转换异常
 *
 * @author daoshenzzg@163.com
 * @date 2019-09-09 10:29
 */
public class ConverterException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConverterException() {
        super();
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(Throwable cause) {
        super(cause);
    }
}

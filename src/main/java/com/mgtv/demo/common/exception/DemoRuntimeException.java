package com.mgtv.demo.common.exception;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-07 14:24
 */
public class DemoRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DemoRuntimeException() {
        super();
    }

    public DemoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoRuntimeException(String message) {
        super(message);
    }

    public DemoRuntimeException(Throwable cause) {
        super(cause);
    }
}

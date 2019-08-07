package com.mgtv.demo.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-07 16:32
 */
@Data
public class Result<T> {
    private static final int SUCCESS = 200;
    private static final int STATUS_404 = 404;
    private static final int STATUS_500 = 500;
    private static final int STATUS_503 = 503;

    private Integer code = SUCCESS;
    private String message = StringUtils.EMPTY;
    private T data;
    private Integer ttl = 0;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.setData(data);
    }

    public Result() {
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS == this.code;
    }

    /**
     * 构建失败返回结果
     *
     * @param code    状态码
     * @param message 提示信息
     * @return 失败返回结果
     */
    public static Result wrapError(int code, String message) {
        return new Result(code, message);
    }

    /**
     * 构建未找到资源的返回结果，比如查询用户没找到，可以使用此方法构建
     *
     * @param message 提示信息
     * @return 错误结果
     */
    public static Result wrap404Error(String message) {
        return wrapError(STATUS_404, message);
    }


    /**
     * 构建系统错误结构，比如由程序引发的空指针错误等
     *
     * @param message 提示信息
     * @return 错误结果
     */
    public static Result wrap500Error(String message) {
        return wrapError(STATUS_500, message);
    }

    /**
     * 系统错误
     *
     * @return
     */
    public static Result wrap500Error() {
        return wrap500Error("系统错误");
    }

    /**
     * 系统熔断保护
     *
     * @return
     */
    public static Result wrapProtectedError() {
        return wrapError(STATUS_503, "系统繁忙");
    }


    public static Result wrapSuccess() {
        return new Result();
    }

    public static <T> Result<T> wrapSuccess(T a) {
        return new Result<>(a);
    }
}

package com.mgtv.demo.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mgtv.demo.annotation.JacksonFill;
import com.mgtv.demo.common.enums.CodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应
 *
 * @author zhiguang@mgtv.com
 * @date 2019-08-07 16:32
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private Integer code = CodeEnum.OK.getCode();
    private String msg = CodeEnum.OK.getMessage();
    @JacksonFill
    private T data;
    private Integer ttl;

    /**
     * 用于ApiLog新增操作的ID记录
     */
    @JsonIgnore
    private Object id;

    public Result(CodeEnum code) {
        this.setCode(code.getCode());
        this.setMsg(code.getMessage());
    }

    public Result(T data) {
        CodeEnum code = CodeEnum.OK;
        this.setCode(code.getCode());
        this.setMsg(code.getMessage());
        this.setData(data);
    }

    @JsonIgnore
    public boolean isOk() {
        return CodeEnum.OK.getCode().equals(this.code);
    }

    /**
     * 附加信息
     *
     * @param remark
     * @return
     */
    public Result withRemark(String remark) {
        this.setMsg(this.getMsg() + "(" + remark + ")");
        return this;
    }

    /**
     * 构建失败返回结果
     *
     * @param code 状态码
     * @return 失败返回结果
     */
    public static Result renderErr(CodeEnum code) {
        return new Result(code);
    }

    /**
     * 构建无效操作返回结果
     *
     * @return
     */
    public static Result renderVain() {
        return new Result(CodeEnum.INVALID_OPERATION);
    }

    /**
     * 构建成功返回结果
     *
     * @return
     */
    public static Result renderOk() {
        return new Result(CodeEnum.OK);
    }

    /**
     * 构建成功返回结果(带数据)
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> renderOk(T data) {
        return new Result<>(data);
    }
}

package com.yb.demo.common.handler;

import com.yb.demo.common.enums.CodeEnum;
import com.yb.demo.common.exception.BizException;
import com.yb.demo.pojo.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.StringJoiner;

/**
 * 全局异常处理
 * <p>
 * 规范：流程跳转尽量避免使用抛 BizException 来做控制。
 *
 * @author daoshenzzg@163.com
 * @date 2019-09-06 18:02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException ex) {
        Result result = Result.renderErr(ex.getCode());
        if (StringUtils.isNotBlank(ex.getRemark())) {
            result.withRemark(ex.getRemark());
        }
        return result;
    }

    /**
     * 参数绑定错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return Result.renderErr(CodeEnum.REQUEST_ERR).withRemark(sj.toString());
    }

    /**
     * 参数校验错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException ex) {
        return Result.renderErr(CodeEnum.REQUEST_ERR).withRemark(ex.getCause().getMessage());
    }

    /**
     * 字段校验不通过异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return Result.renderErr(CodeEnum.REQUEST_ERR).withRemark(sj.toString());
    }

    /**
     * Controller参数绑定错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return Result.renderErr(CodeEnum.REQUEST_ERR).withRemark(ex.getMessage());
    }

    /**
     * 处理方法不支持异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return Result.renderErr(CodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 其他未知异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.renderErr(CodeEnum.SERVER_ERR);
    }
}

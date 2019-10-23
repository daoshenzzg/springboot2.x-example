package com.mgtv.demo.annotation;

import com.mgtv.demo.common.enums.FillTypeEnum;

import java.lang.annotation.*;

/**
 * 自动填充
 *
 * @author daoshenzzg@163.com
 * @date 2019-10-23 19:07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JacksonFill {
    FillTypeEnum value() default FillTypeEnum.BRACE;
}

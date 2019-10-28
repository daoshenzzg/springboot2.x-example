package com.yb.demo.common.handler;

import com.yb.demo.pojo.response.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-07 16:56
 */
@Aspect
@Component
public class TtlHandler {

    @Pointcut(value = "execution(public * com.yb.demo.controller..*.*(..))")
    public void start() {
    }

    @Around("start()")
    public Result access(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Result result = (Result) joinPoint.proceed();
        long end = System.currentTimeMillis();
        result.setTtl(Math.toIntExact(end - start));
        return result;
    }
}

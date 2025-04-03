package com.argyle.argylepicturebackend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1) // 优先级高于权限切面（数字越小优先级越高）
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.argyle..controller.*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        // 记录方法入口日志
        log.info("===> 方法调用 [{}#{}] 参数: {}", className, methodName, Arrays.toString(args));
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed(); // 执行后续切面及业务方法
            long cost = System.currentTimeMillis() - startTime;

            // 记录正常返回日志
            log.info("<=== 方法返回 [{}#{}] 耗时: {}ms 结果: {}",
                className, methodName, cost, truncateResult(result));
            return result;
        } catch (Exception ex) {
            long cost = System.currentTimeMillis() - startTime;

            // 记录异常日志
            log.error("<=== 方法异常 [{}#{}] 耗时: {}ms 异常: {}",
                className, methodName, cost, ex.getMessage(), ex);
            throw ex;
        }
    }

    // 截断过长的结果（防止日志过大）
    private Object truncateResult(Object result) {
        if (result instanceof String && ((String)result).length() > 200) {
            return ((String)result).substring(0, 200) + "...[TRUNCATED]";
        }
        return result;
    }


}

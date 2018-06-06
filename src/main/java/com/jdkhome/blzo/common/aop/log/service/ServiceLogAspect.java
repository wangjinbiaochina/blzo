package com.jdkhome.blzo.common.aop.log.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zzhoo8 on 2017/9/8.
 */
@Aspect
@Component
public class ServiceLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(* com.jdkhome.blzo.service..*.*(..))")
    private void serviceAspect() {

    }

    // 请求前
    @Before(value = "serviceAspect()")//&& @annotation(com.jdkhome.blzo.common.aop.log.service.ServiceLog)
    public void methodBefore(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        ServiceLog serviceLog = method.getAnnotation(ServiceLog.class);

        if (serviceLog == null) {
            return;
        }

        // 所有参数
        Object[] objects = joinPoint.getArgs();
        StringBuilder args = new StringBuilder("");
        for (Object obj : objects) {
            // 有些调用服务时，参数为null，需要跳过
            args.append((obj != null) ? obj.toString() : "null").append(", ");
        }
        String argStr = args.toString();
        if (args.length() > 2) {
            argStr = args.substring(0, args.length() - 2);
        }

        logger.info("Service => [{}] {}.{}({})", serviceLog.value(), joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), argStr);
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "serviceAspect() && @annotation(com.jdkhome.blzo.common.aop.log.service.ServiceLog)")
    public void methodAfterReturing(Object o) {
        // 所有参数
//        Object[] objects = joinPoint.getArgs();
//        StringBuilder args = new StringBuilder("");
//        for (Object obj : objects) {
//            args.append(obj.toString());
//        }

//        logger.info("结果 {}", JSON.toJSONString(o));
    }


}

package com.jdkhome.blzo.common.aop.log.controller;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by fc on 17/4/18.
 */

@Aspect
@Component
public class ControllerLogRecordAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerLogRecordAspect.class);


    //申明一个切点里面是execution表达式
    @Pointcut("execution(* com.jdkhome.blzo.controller..*.*(..))")
    private void controllerAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()&&@annotation(com.jdkhome.blzo.common.aop.log.controller.ControllerLog)||@annotation(com.jdkhome.blzo.common.aop.log.controller.RequestLog)")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder sb = new StringBuilder();
        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements()) {
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=").append(values[0]);
                } else {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
        }

        //打印请求内容
        StringBuffer logStr = new StringBuffer();
        logStr.append("Controller(In) =>");
        logStr.append(" ip: ");
        logStr.append(request.getHeader("X-Forwarded-For"));
        logStr.append(" [" + request.getMethod() + "]");
        logStr.append(request.getRequestURL());
        logStr.append(" param: ");
        logStr.append(sb);
        logStr.append(" 请求类名: ");
        logStr.append(joinPoint.getTarget().getClass().getSimpleName());
        logStr.append(" 请求类方法: ");
        logStr.append(joinPoint.getSignature());


        logger.info(logStr.toString());
    }


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()&&@annotation(com.jdkhome.blzo.common.aop.log.controller.ControllerLog)||@annotation(com.jdkhome.blzo.common.aop.log.controller.ResponseLog)")
    public void methodAfterReturing(Object o) {
        logger.info("Controller(Out) =>" + JSON.toJSONString(o));
    }
}

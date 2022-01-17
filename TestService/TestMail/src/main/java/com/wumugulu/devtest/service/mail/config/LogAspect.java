package com.wumugulu.devtest.service.mail.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {
    // 匹配com.cc.springboot.controller包下所有类的、
    // 所有方法的执行作为切入点
    @Pointcut("execution(* com.wumugulu.devtest..*Controller.*(..))")
    public void controllerMappingMethod() {}

    @Before("controllerMappingMethod()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 记录下请求内容
        Enumeration<String> enu = request.getParameterNames();
        Map<String, String> paraMap = new HashMap<>();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            paraMap.put(name, request.getParameter(name));
        }
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            // ServletRequest, ServletResponse, MultipartFile不能序列化，从入参里排除，
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                arguments[i] = args[i].getClass().getName();
            } else {
                arguments[i] = args[i];
            }
        }
        //System.err.println("joinPoint.getArgs() = " + JSONArray.toJSONString( joinPoint.getArgs()));
        log.info("AspectLog - [[REQUEST]]: {}, {}, {}, {}, {}, {}",
                request.getRemoteAddr(),
                request.getRequestURI(),
                joinPoint.getTarget().getClass().getName(), // .getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                JSONUtil.toJsonStr(request.getParameterMap()),
                JSONUtil.toJsonStr(arguments));
    }

    @AfterReturning(returning = "result", pointcut = "controllerMappingMethod()")
    public void doAfterReturning(Object result) {
        // 处理完请求，记录返回内容
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        // System.err.println("prepare to record response log ...");
        log.info("AspectLog - [[RESPONSE]]: {}, {}, {}",
                response.getStatus(),
                JSONUtil.toJsonStr(result),
                "" //JSON.toJSONString(attrMap)
        );
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerMappingMethod()")
    public void doRecoveryActions(Throwable ex)
    {
        System.out.println("目标方法中抛出的异常:" + ex);
        System.out.println("模拟Advice对异常的修复...");
        log.info("AspectLog - [[RESPONSE]]: {}, {}",
                "Exception",
                ex.getMessage());
    }
}

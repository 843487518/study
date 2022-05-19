package com.zxk.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/4/24  14:10
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**内部类
     *我们想让日志记录 :
     * url请求
     * 访问对象的IP地址
     * 调用了什么方法
     * 传入了什么参数
     * */
    private class content{
        private String url;
        private String ip;
        private String method;
        private Object[] args;
        public content(String url, String ip, String method, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.method = method;
            this.args = args;
        }
        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", method='" + method + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
    @Before("execution(* com.zxk.study.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("执行了前置方法");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String method=
                joinPoint.getSignature().getDeclaringTypeName()
                        +"."+
                        joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        content content = new content(url,ip,method,args);
        log.info("Request:{}",content);
    }
    @AfterReturning(value = "execution(* com.zxk.study.controller.*.*(..))",returning = "baseResult")
    public void afterRunning(BaseResult baseResult){
        System.out.println("执行了后置方法");
        log.info(baseResult.toString());
    }
}

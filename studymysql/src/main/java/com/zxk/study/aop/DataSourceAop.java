package com.zxk.study.aop;

import com.zxk.study.beans.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/26  17:17
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.zxk.study.annotation.Master) " +
            "&& (execution(* com.zxk.study.service..*.select*(..)) " +
            "|| execution(* com.zxk.study.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.zxk.study.annotation.Master) " +
            "|| execution(* com.zxk.study.service..*.insert*(..)) " +
            "|| execution(* com.zxk.study.service..*.add*(..)) " +
            "|| execution(* com.zxk.study.service..*.update*(..)) " +
            "|| execution(* com.zxk.study.service..*.edit*(..)) " +
            "|| execution(* com.zxk.study.service..*.delete*(..)) " +
            "|| execution(* com.zxk.study.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.zxk.study.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}

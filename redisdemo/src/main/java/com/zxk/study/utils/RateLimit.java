package com.zxk.study.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Zhouxinkai
 * @Description: 限流注解
 * 属性分别为：限流key，时间间隔time，最大访问次数count
 * @date 2022/5/10  9:41
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    boolean required() default true;
    String key() default "limit";

    int time() default 5;

    int count() default 5;
}

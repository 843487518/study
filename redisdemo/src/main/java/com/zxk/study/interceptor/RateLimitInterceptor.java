package com.zxk.study.interceptor;

import com.zxk.study.utils.PassToken;
import com.zxk.study.utils.RateLimit;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collections;

/**
 * @author Zhouxinkai
 * @Description: 限流拦截器
 * @date 2022/5/10  9:43
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 注入StringRedisTemplate、DefaultRedisScript
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DefaultRedisScript redisScript;

    /**
     * 实现具体的拦截业务逻辑
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        System.out.println(handler instanceof HandlerMethod);
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
        //检查是否有RateLimit注解，没有则跳过
        if (method.isAnnotationPresent(RateLimit.class)) {
            //获取方法上面的注解
            RateLimit rateLimit = method.getAnnotation(RateLimit.class);
            //判断是否存在
            if(rateLimit == null){
                //不存在注解，直接跳过
                return true;
            }
            //执行到这里说明，请求的方法上面存在限流注解，所以在这里执行限流逻辑
            Long execute = (Long) stringRedisTemplate.execute(redisScript,
                    Collections.singletonList(rateLimit.key()),
                    String.valueOf(rateLimit.count()),
                    String.valueOf(rateLimit.time()));
            if (execute == 0){
                System.out.println("xianliu");
                response.sendError(500,"已限流");
                return false;
            }
            System.out.println(execute);
        }
        return true;
    }
}

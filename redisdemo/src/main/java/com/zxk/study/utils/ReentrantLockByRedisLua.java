package com.zxk.study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/7  22:44
 */
@Component
public class ReentrantLockByRedisLua {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //声明脚本
    private static final DefaultRedisScript<Long> LRL_SCRIPT;
    private static final DefaultRedisScript<Long> LRUL_SCRIPT;


    static {
        //在静态代码块中实例化脚本，等待调用
        LRL_SCRIPT = new DefaultRedisScript<>();
        LRL_SCRIPT.setLocation(new ClassPathResource("Reentrantlock.lua"));
        LRL_SCRIPT.setResultType(Long.class);
        LRUL_SCRIPT = new DefaultRedisScript<>();
        LRUL_SCRIPT.setLocation(new ClassPathResource("ReentrantUnlock.lua"));
        LRUL_SCRIPT.setResultType(Long.class);
    }

    public boolean lock(String type,long outtime) {
        //获取当前线程id
        String id = Utils.ID_PREFIX+Thread.currentThread().getId();
        //尝试加锁
        Long execute = stringRedisTemplate.execute(LRL_SCRIPT,
                Collections.singletonList(type),
                id,
                outtime);
        return execute.equals(1);
    }

    public void unlock(String type,long outtime){
        //获取当前线程id
        String id = Utils.ID_PREFIX+Thread.currentThread().getId();
        //调用Lua脚本解锁
        stringRedisTemplate.execute(LRUL_SCRIPT,
                Collections.singletonList(type),
                id,
                outtime);
    }
}

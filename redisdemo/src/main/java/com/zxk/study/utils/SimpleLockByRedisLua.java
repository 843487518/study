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
 * @Description: 在解锁时，使用Lua脚步，确保解锁操作的原子性
 * @date 2022/5/7  17:25
 */
@Component
public class SimpleLockByRedisLua {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final DefaultRedisScript<Long> LC_SCRIPT;

    static {
        LC_SCRIPT = new DefaultRedisScript<>();
        LC_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        LC_SCRIPT.setResultType(Long.class);
    }
    /**
     * 加锁
     * @param type，不同的业务使用不用的type
     * @param outtime，有效期
     * @return
     */
    public boolean lock(String type,long outtime) {
        //获取当前线程id
        String id = Utils.ID_PREFIX+Thread.currentThread().getId();
        //尝试加锁
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(type, id, outtime, TimeUnit.MINUTES);
        //返回加锁结果
        return Boolean.TRUE.equals(aBoolean);
    }

    public void unlock(String type){
        //获取当前线程id
        String id = Utils.ID_PREFIX+Thread.currentThread().getId();
        //调用
        stringRedisTemplate.execute(LC_SCRIPT,
                Collections.singletonList(type),
                id);
    }
}

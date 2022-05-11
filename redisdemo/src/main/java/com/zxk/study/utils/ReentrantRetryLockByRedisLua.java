package com.zxk.study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Zhouxinkai
 * @Description:可重入和可重试
 * @date 2022/5/8  9:41
 */
@Component
public class ReentrantRetryLockByRedisLua {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //声明脚本
    private static final DefaultRedisScript<Long> LRUL_SCRIPT;
    private static final DefaultRedisScript<Long> LRRL_SCRIPT;


    static {
        //在静态代码块中实例化脚本，等待调用
        LRUL_SCRIPT = new DefaultRedisScript<>();
        LRUL_SCRIPT.setLocation(new ClassPathResource("ReentrantUnlock.lua"));
        LRUL_SCRIPT.setResultType(Long.class);
        LRRL_SCRIPT = new DefaultRedisScript<>();
        LRRL_SCRIPT.setLocation(new ClassPathResource("ReentrantRetrylock.lua"));
        LRRL_SCRIPT.setResultType(Long.class);
    }

    /**
     *加锁，可重入，可重试
     * @param type,不同的业务使用不用的type来加锁
     * @param outtime,锁的有效时间
     * @param maxtime,最大尝试次数
     * @return
     */
    public boolean retrylock(String type,long outtime,long maxtime) {
        //获取当前线程id
        String id = Utils.ID_PREFIX+Thread.currentThread().getId();
        //尝试加锁
        Long execute = stringRedisTemplate.execute(LRRL_SCRIPT,
                Collections.singletonList(type),
                id,
                //outtime,
                String.valueOf(outtime),
                //maxtime
                String.valueOf(maxtime) );
        assert execute != null;
        return execute.equals(1L);
    }

    /**
     * 解锁
     * @param type,不同的业务使用不用的type来加锁
     * @param outtime,锁的有效时间
     * @return
     */
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

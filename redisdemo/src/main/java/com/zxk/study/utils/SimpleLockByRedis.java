package com.zxk.study.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
/**
 * @author Zhouxinkai
 * @Description: 使用redis实现简单的加锁和解锁，
 * key中存储锁竞争对象，相同的业务需要使用相同的key，
 * value采用String类型，存储当前的线程id，在释放锁的时候需要用到
 * @date 2022/5/7  16:37
 */
@Component
public class SimpleLockByRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        String threadId = Utils.ID_PREFIX+Thread.currentThread().getId();
        //获取锁中的线程标识
        String id = stringRedisTemplate.opsForValue().get(type);
        if (threadId.equals(id)){
            //是当前线程上的锁就解锁
            stringRedisTemplate.delete(type);
        }
    }
}

package com.zxk.study.service.impl;

import com.zxk.study.mapper.UserMapper;
import com.zxk.study.module.dto.UserDto;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.RandomOutTimeUtil;
import com.zxk.study.utils.ReentrantRetryLockByRedisLua;
import com.zxk.study.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/8  10:53
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 导入Redis操作类StringRedisTemplate
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ReentrantRetryLockByRedisLua reentrantRetryLockByRedisLua;

    @Resource
    private UserMapper userMapper;

//    private static final DefaultRedisScript<Long> LRRL_SCRIPT;
//
//    static {
//        LRRL_SCRIPT = new DefaultRedisScript<>();
//        LRRL_SCRIPT.setLocation(new ClassPathResource("ReentrantRetrylock.lua"));
//        LRRL_SCRIPT.setResultType(Long.class);
//    }

    /**
     * 通过id查询用户详情
     * @param user
     * @return
     */
    @SneakyThrows
    @Override

    //加锁位置：1.整个方法加锁（本地锁、分布式锁）
//    @Transactional(rollbackFor = Exception.class)
    public UserDto getUserById(UserDto user) {
        //1.查询缓存
        String s = stringRedisTemplate.opsForValue().get(Utils.USERID_PREFIX + user.getId().toString());
        //2.判断缓存中是否存在
        if (s != null){
            //3.存在就反序列化并返回查询到的数
            System.out.println(s);
            UserDto userDto = Utils.jsonMapper.readValue(s, UserDto.class);
            System.out.println("来自缓存");
            System.out.println(userDto);
            return null;

        }
            //8.存入缓存,需要加锁，防止缓存击穿问题
        boolean retrylock = reentrantRetryLockByRedisLua.retrylock(Utils.LOCKTYPE1,
                Utils.LOCK_OUT_TIME,
                Utils.RETRYLOCK_MAX);
        //9.判断加锁是否成功
        if (retrylock){
                //4.缓存中不存在，查询数据库
            UserDto userDto = userMapper.selectOne(user);
                //5.如果数据库中存在，返回数据，并添加缓存
            if (userDto != null){
                    //6.获取随机的有效时间，减少缓存雪崩的概率,此处操作已经移入
                    //7.对数据进行序列化
                String s1 = Utils.jsonMapper.writeValueAsString(userDto);
                //加锁成功的线程才对缓存进行重写
                String key = Utils.USERID_PREFIX + userDto.getId().toString();
                long timeout = Utils.CACHE_OUT_TIME+RandomOutTimeUtil.getRandomOutTime();
                stringRedisTemplate.opsForValue().set(key,s1,timeout,TimeUnit.SECONDS);
                //10.最后返回查询到的数据
                System.out.println("来自数据库");
                System.out.println(userDto);
                return userDto;
            }
            //11.执行到这里，说明缓存和数据库都不存在数据，将空数据存入缓存，防止缓存穿透
            stringRedisTemplate.opsForValue().set(Utils.USERID_PREFIX + userDto.getId().toString(),"");
            System.out.println("不存在");
            return null;
        }
        System.out.println("请重试");
        return null;
    }

    @Override
    public List<UserDto> getUserAll(int page) {
        return null;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public int update(UserDto user) {
//        //1.更新用户信息
//        int update = userMapper.update(user);
//        //2.如果修改成功，删除缓存
//        if ( update == 1){
//            stringRedisTemplate.delete(Utils.USERID_PREFIX + user.getId().toString());
//            return 1;
//        }
        return 0;
    }
}

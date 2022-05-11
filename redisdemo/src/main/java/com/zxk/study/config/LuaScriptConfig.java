package com.zxk.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author Zhouxinkai
 * @Description: 装配一个lua脚本对象，等待RedisTemplate调用
 * @date 2022/5/10  9:21
 */

@Configuration
public class LuaScriptConfig {
    @Bean
    public DefaultRedisScript<Long> redisluaScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        //读取 lua 脚本
        //redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
        redisScript.setLocation(new ClassPathResource("fixedwindow.lua"));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

}

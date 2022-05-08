package com.zxk.study.controller;

import com.zxk.study.module.dto.UserDto;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Zhouxinkai
 * @Description: 在用户的查询和修改过程中用redis充当缓存，包括：缓存更新、击穿、雪崩、穿透等问题的结局思路
 * @date 2022/5/8  10:09
 */
@Controller
@RequestMapping("/v1/redis/cache")
public class RedisCacheController {

    @Resource
    private UserService userService;

    //由于只要是针对缓存，所以这里程序结果有后台打印，返回值用void
    @RequestMapping("/one/{id}")
    public void getUserById(@PathVariable long id){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userService.getUserById(userDto);
    }

    @RequestMapping("/update")
    public void updateUser(@RequestBody UserDto user){
        userService.update(user);
    }

}

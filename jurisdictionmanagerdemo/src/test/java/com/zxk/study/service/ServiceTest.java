package com.zxk.study.service;

import com.zxk.study.module.dto.TbUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/19  12:56
 */

@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void login(){
        TbUserDTO tbUserDTO = TbUserDTO.builder()
                .username("zhouxk")
                .password("123456")
                .build();
        Map map = userService.login(tbUserDTO);
        System.out.println(map);

    }
}

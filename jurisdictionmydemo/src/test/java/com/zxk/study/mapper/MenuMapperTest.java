package com.zxk.study.mapper;

import com.zxk.study.module.dto.SysMenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/21  19:55
 */
@SpringBootTest
public class MenuMapperTest {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Test
    void menuMapperList(){
        List<SysMenuDTO> sysMenuDTOS = sysMenuMapper.selectAllAll();
        System.out.println(sysMenuDTOS);
    }
}

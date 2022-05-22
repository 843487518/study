package com.zxk.study.mapper;

import com.zxk.study.module.dto.*;
import org.aspectj.lang.annotation.AfterReturning;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/22  23:43
 */
@SpringBootTest
public class MapperTest {


    @Autowired
    JmUserMapper jmUserMapper;
    @Autowired
    JmRoleMapper jmRoleMapper;
    @Autowired
    JmMenuMapper jmMenuMapper;
    @Autowired
    JmRoleMenuMapper jmRoleMenuMapper;
    @Autowired
    JmUserRoleMapper jmUserRoleMapper;
    @Test
    void addData(){
        for (int i = 0; i < 20; i++) {
            JmUserDTO jmUserDTO = JmUserDTO.builder()
                    .username("测试用户"+i)
                    .mobile("182"+i+"135"+i+100)
                    .password("pwd"+i)
                    .deleteFlag(0)
                    .build();
            jmUserMapper.insert(jmUserDTO);

            JmRoleDTO jmRoleDTO = JmRoleDTO.builder()
                    .roleName("测试角色"+i)
                    .remark("备注"+i)
                    .createUserId(1L)
                    .deleteFlag(0)
                    .build();
            jmRoleMapper.insert(jmRoleDTO);

            JmMenuDTO jmMenuDTO = JmMenuDTO.builder()
                    .menuName("测试菜单"+i)
                    .parentId(0L)
                    .url("url"+i)
                    .icon("icon"+i)
                    .createUserId(1L)
                    .deleteFlag(0)
                    .build();
            jmMenuMapper.insert(jmMenuDTO);

            for (int j = 1; j < i; j++) {
                //绑定菜单跟角色的关系
                JmRoleMenuDTO jmRoleMenuDTO = JmRoleMenuDTO.builder()
                        .menuId((long) i)
                        .roleId((long) j)
                        .createAuthority(0)
                        .deleteAuthority(0)
                        .updateAuthority(0)
                        .queryAuthority(0)
                        .createUserId(1L)
                        .deleteFlag(0)
                        .build();
                jmRoleMenuMapper.insert(jmRoleMenuDTO);

                //绑定角色跟账号的关系
                JmUserRoleDTO jmUserRoleDTO = JmUserRoleDTO.builder()
                        .userId((long) i)
                        .roleId((long) j)
                        .createUserId(1L)
                        .deleteFlag(0)
                        .build();
                jmUserRoleMapper.insert(jmUserRoleDTO);
            }


        }

    }
}

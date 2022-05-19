package com.zxk.study.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxk.study.module.dto.*;
import com.zxk.study.service.MenuService;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/18  21:14
 */

@SpringBootTest
public class MapperTest {


    @Autowired
    private MyBatisPTestMapper myBatisPTestMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    void contextLoads() {

        for(long i=4;i<10;i++) {
            //往菜单表中添加数据
            SysMenuDTO sysMenuDTO = SysMenuDTO.builder()
                    .url("url"+i)
                    .parentId(0L)
                    .name("菜单"+i)
                    .createUserId(1L).build();
            sysMenuMapper.insert(sysMenuDTO);

            //往角色表中添加数据
            SysRoleDTO sysRoleDTO = SysRoleDTO.builder()
                    .roleName("大聪明"+i+"号")
                    .createUserId(1L)
                    .deleteFlag(0)
                    .build();
            sysRoleMapper.insert(sysRoleDTO);

            //往账号表中添加数据
            TbUserDTO tbUserDTO = TbUserDTO.builder()
                    .username("test"+i)
                    .password("12345")
                    .mobile("182"+i+"456")
                    .deleteFlag(0)
                    .build();
            tbUserMapper.insert(tbUserDTO);

            //往角色菜单表中添加数据
            for (long j=1;j<i;j++){
                SysRoleMenuDTO sysRoleMenuDTO = SysRoleMenuDTO.builder()
                        .roleId(i)
                        .menuId(j)
                        .createUserId(1L)
                        .deleteFlag(0)
                        .build();
                sysRoleMenuMapper.insert(sysRoleMenuDTO);
            }

            //往账号角色表中添加数据
            SysUserRoleDTO sysUserRoleDTO = SysUserRoleDTO.builder()
                    .userId(i)
                    .roleId(i)
                    .createUserId(1L)
                    .deleteFlag(0)
                    .build();
            sysUserRoleMapper.insert(sysUserRoleDTO);
        }
    }

    @Test
    void setSysRoleMapperTest(){
        SysRoleDTO sysRoleDTO = SysRoleDTO.builder()
                .role_id(1L)
                .build();
        List<SysMenuDTO> sysMenuDTOS = sysRoleMapper.selectMenu(sysRoleDTO);
        sysMenuDTOS.forEach(System.out::println);
    }

    @Test
    void myBatisPlusTest(){
        List<TbUser> tbUserS = myBatisPTestMapper.selectList(null);
        System.out.println(tbUserS.size());
        tbUserS.forEach(System.out::println);
    }

    @Test
    void tbUserTest(){
        TbUserDTO tbUserDTO = TbUserDTO.builder()
                .deleteFlag(0)
                .build();
        List<TbUserDTO> tbUserDTOS = tbUserMapper.selectAll(tbUserDTO);
        tbUserDTOS.forEach(System.out::println);
    }

    @Test
    void sysMenuTest(){
        SysMenuDTO sysMenuDTO = SysMenuDTO.builder()
                .deleteFlag(0)
                .build();
        List<SysMenuDTO> sysMenuDTOS = sysMenuMapper.selectAll(sysMenuDTO);
        sysMenuDTOS.forEach(System.out::println);
    }

    @Test
    void roleMenuTest(){
        SysRoleMenuDTO sysRoleMenuDTO = SysRoleMenuDTO.builder()
                .deleteFlag(0)
                .build();
        List<SysRoleMenuDTO> sysRoleMenuDTOS = sysRoleMenuMapper.selectAll(sysRoleMenuDTO);
        sysRoleMenuDTOS.forEach(System.out::println);
    }
}

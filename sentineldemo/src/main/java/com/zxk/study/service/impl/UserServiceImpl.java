package com.zxk.study.service.impl;

import java.util.List;
import com.zxk.study.mapper.SysUserMapper;
import com.zxk.study.module.SysUserDTO;
import com.zxk.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_user  用户表
* @author zhouxx
* @create	2022-04-26 16:39:26
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 SysUserMapper sysUserMapper;

		 @Override
		 public SysUserDTO query(SysUserDTO sysUserDTO){
		        return sysUserMapper.selectOne(sysUserDTO);
		 }
		 @Override
		 public List<SysUserDTO > queryList(SysUserDTO sysUserDTO){
		        return sysUserMapper.selectAll(sysUserDTO);
		 }
		 @Override
		 public int add(SysUserDTO sysUserDTO){
		        return sysUserMapper.insert(sysUserDTO);
		 }
		 @Override
		 public int modify(SysUserDTO sysUserDTO){
		        return sysUserMapper.update(sysUserDTO);
		 }
		 @Override
		 public int delete(SysUserDTO sysUserDTO){
		        return sysUserMapper.delete(sysUserDTO);
		 }

}

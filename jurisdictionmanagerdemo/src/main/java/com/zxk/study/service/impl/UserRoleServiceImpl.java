package com.zxk.study.service.impl;

import java.util.List;
import com.zxk.study.mapper.SysUserRoleMapper;
import com.zxk.study.module.dto.SysUserRoleDTO;
import com.zxk.study.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

		 @Autowired
		 SysUserRoleMapper sysUserRoleMapper;

		 @Override
		 public SysUserRoleDTO query(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.selectOne(sysUserRoleDTO);
		 }
		 @Override
		 public List<SysUserRoleDTO > queryList(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.selectAll(sysUserRoleDTO);
		 }
		 @Override
		 public int add(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.insert(sysUserRoleDTO);
		 }
		 @Override
		 public int modify(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.update(sysUserRoleDTO);
		 }
		 @Override
		 public int delete(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.delete(sysUserRoleDTO);
		 }

}

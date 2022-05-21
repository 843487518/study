package com.zxk.study.service.impl;

import com.zxk.study.mapper.SysRoleMapper;
import com.zxk.study.module.dto.SysRoleDTO;
import com.zxk.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Service
public class RoleServiceImpl implements RoleService {

		 @Autowired
		 SysRoleMapper sysRoleMapper;

		 @Override
		 public SysRoleDTO query(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.selectOne(sysRoleDTO);
		 }
		 @Override
		 public List<SysRoleDTO > queryList(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.selectAll(sysRoleDTO);
		 }
		 @Override
		 public int add(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.insert(sysRoleDTO);
		 }
		 @Override
		 public int modify(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.update(sysRoleDTO);
		 }
		 @Override
		 public int delete(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.delete(sysRoleDTO);
		 }

}

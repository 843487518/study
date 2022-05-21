package com.zxk.study.service.impl;

import com.zxk.study.mapper.SysRoleMenuMapper;
import com.zxk.study.module.dto.SysRoleMenuDTO;
import com.zxk.study.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

		 @Autowired
		 SysRoleMenuMapper sysRoleMenuMapper;

		 @Override
		 public SysRoleMenuDTO query(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.selectOne(sysRoleMenuDTO);
		 }
		 @Override
		 public List<SysRoleMenuDTO > queryList(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.selectAll(sysRoleMenuDTO);
		 }
		 @Override
		 public int add(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.insert(sysRoleMenuDTO);
		 }
		 @Override
		 public int modify(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.update(sysRoleMenuDTO);
		 }
		 @Override
		 public int delete(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.delete(sysRoleMenuDTO);
		 }

}

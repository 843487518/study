package com.zxk.study.service;

import com.zxk.study.module.dto.SysUserRoleDTO;

import java.util.List;

/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
public interface UserRoleService {

		 public SysUserRoleDTO query(SysUserRoleDTO sysUserRoleDTO);
		 public List<SysUserRoleDTO > queryList(SysUserRoleDTO sysUserRoleDTO);
		 public int add(SysUserRoleDTO sysUserRoleDTO);
		 public int modify(SysUserRoleDTO sysUserRoleDTO);
		 public int delete(SysUserRoleDTO sysUserRoleDTO);

}

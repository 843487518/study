package com.zxk.study.service;

import com.zxk.study.module.dto.SysRoleMenuDTO;
import java.util.List;

/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
public interface RoleMenuService {

		 public SysRoleMenuDTO query(SysRoleMenuDTO sysRoleMenuDTO);
		 public List<SysRoleMenuDTO > queryList(SysRoleMenuDTO sysRoleMenuDTO);
		 public int add(SysRoleMenuDTO sysRoleMenuDTO);
		 public int modify(SysRoleMenuDTO sysRoleMenuDTO);
		 public int delete(SysRoleMenuDTO sysRoleMenuDTO);

}

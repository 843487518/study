package com.zxk.study.service;

import com.zxk.study.module.dto.SysRoleDTO;
import java.util.List;

/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
public interface RoleService {

		 public SysRoleDTO query(SysRoleDTO sysRoleDTO);
		 public List<SysRoleDTO > queryList(SysRoleDTO sysRoleDTO);
		 public int add(SysRoleDTO sysRoleDTO);
		 public int modify(SysRoleDTO sysRoleDTO);
		 public int delete(SysRoleDTO sysRoleDTO);

}

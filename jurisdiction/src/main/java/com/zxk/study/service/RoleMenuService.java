package com.zxk.study.service;

import com.zxk.study.module.dto.JmRoleMenuDTO;
import java.util.List;


/**
* Table:jm_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
public interface RoleMenuService {

		 public JmRoleMenuDTO query(JmRoleMenuDTO jmRoleMenuDTO);
		 public List<JmRoleMenuDTO > queryList(JmRoleMenuDTO jmRoleMenuDTO);
		 public int add(JmRoleMenuDTO jmRoleMenuDTO);
		 public int modify(JmRoleMenuDTO jmRoleMenuDTO);
		 public int delete(JmRoleMenuDTO jmRoleMenuDTO);

}

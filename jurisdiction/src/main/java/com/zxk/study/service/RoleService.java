package com.zxk.study.service;

import com.zxk.study.module.dto.JmRoleDTO;
import java.util.List;

/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
public interface RoleService {

		 public JmRoleDTO query(JmRoleDTO jmRoleDTO);
		 public List<JmRoleDTO > queryList(JmRoleDTO jmRoleDTO);
		 public int add(JmRoleDTO jmRoleDTO);
		 public int modify(JmRoleDTO jmRoleDTO);
		 public int delete(JmRoleDTO jmRoleDTO);

}

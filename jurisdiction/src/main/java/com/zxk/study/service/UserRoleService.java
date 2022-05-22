package com.zxk.study.service;

import com.zxk.study.module.dto.JmUserRoleDTO;
import java.util.List;

/**
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
public interface UserRoleService {

		 public JmUserRoleDTO query(JmUserRoleDTO jmUserRoleDTO);
		 public List<JmUserRoleDTO > queryList(JmUserRoleDTO jmUserRoleDTO);
		 public int add(JmUserRoleDTO jmUserRoleDTO);
		 public int modify(JmUserRoleDTO jmUserRoleDTO);
		 public int delete(JmUserRoleDTO jmUserRoleDTO);

}

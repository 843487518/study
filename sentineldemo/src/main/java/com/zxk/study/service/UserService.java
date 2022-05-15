  package com.zxk.study.service;


import com.zxk.study.module.SysUserDTO;

import java.util.List;


/**
* Table:sys_user  用户表
* @author zhouxx
* @create	2022-04-26 16:39:26
*/
public interface UserService {

		 public SysUserDTO query(SysUserDTO sysUserDTO);
		 public List<SysUserDTO > queryList(SysUserDTO sysUserDTO);
		 public int add(SysUserDTO sysUserDTO);
		 public int modify(SysUserDTO sysUserDTO);
		 public int delete(SysUserDTO sysUserDTO);

}

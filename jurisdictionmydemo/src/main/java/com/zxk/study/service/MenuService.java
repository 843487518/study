package com.zxk.study.service;

import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.utils.BaseResult;

import java.util.List;

/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
public interface MenuService {

		 public SysMenuDTO query(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > queryList(SysMenuDTO sysMenuDTO);
		 public BaseResult add(SysMenuDTO sysMenuDTO);
		 public BaseResult modify(SysMenuDTO sysMenuDTO);
		 public int delete(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > queryListAll();

}

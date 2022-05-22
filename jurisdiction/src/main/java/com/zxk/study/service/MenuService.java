package com.zxk.study.service;

import com.zxk.study.module.dto.JmMenuDTO;
import java.util.List;

/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
public interface MenuService {

		 public JmMenuDTO query(JmMenuDTO jmMenuDTO);
		 public List<JmMenuDTO > queryList(JmMenuDTO jmMenuDTO);
		 public int add(JmMenuDTO jmMenuDTO);
		 public int modify(JmMenuDTO jmMenuDTO);
		 public int delete(JmMenuDTO jmMenuDTO);

}

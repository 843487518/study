package com.zxk.study.service;

import com.zxk.study.module.dto.JmUserDTO;
import com.zxk.study.utils.BaseResult;

import java.util.List;

/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
public interface UserService {

		 public JmUserDTO query(JmUserDTO jmUserDTO);
		 public List<JmUserDTO > queryList(JmUserDTO jmUserDTO);
		 public int add(JmUserDTO jmUserDTO);
		 public int modify(JmUserDTO jmUserDTO);
		 public int delete(JmUserDTO jmUserDTO);
		 BaseResult login(JmUserDTO tbUserDTO);
}

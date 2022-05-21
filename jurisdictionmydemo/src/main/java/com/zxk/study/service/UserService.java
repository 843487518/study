package com.zxk.study.service;

import com.zxk.study.module.dto.TbUserDTO;
import com.zxk.study.utils.BaseResult;

import java.util.List;
import java.util.Map;

/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
public interface UserService {

		 public TbUserDTO query(TbUserDTO tbUserDTO);
		 public List<TbUserDTO > queryList(TbUserDTO tbUserDTO);
		 public int add(TbUserDTO tbUserDTO);
		 public int modify(TbUserDTO tbUserDTO);
		 public int delete(TbUserDTO tbUserDTO);
		 public BaseResult login(TbUserDTO tbUserDTO);
}

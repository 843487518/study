package com.zxk.study.service.impl;

import com.zxk.study.mapper.JmUserRoleMapper;
import com.zxk.study.module.dto.JmUserRoleDTO;
import com.zxk.study.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

		 @Autowired
		 JmUserRoleMapper jmUserRoleMapper;

		 @Override
		 public JmUserRoleDTO query(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.selectOne(jmUserRoleDTO);
		 }
		 @Override
		 public List<JmUserRoleDTO > queryList(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.selectAll(jmUserRoleDTO);
		 }
		 @Override
		 public int add(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.insert(jmUserRoleDTO);
		 }
		 @Override
		 public int modify(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.update(jmUserRoleDTO);
		 }
		 @Override
		 public int delete(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.delete(jmUserRoleDTO);
		 }

}

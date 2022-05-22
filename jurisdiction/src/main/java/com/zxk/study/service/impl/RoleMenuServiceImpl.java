package com.zxk.study.service.impl;

import com.zxk.study.mapper.JmRoleMenuMapper;
import com.zxk.study.module.dto.JmRoleMenuDTO;
import com.zxk.study.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* Table:jm_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

		 @Autowired
		 JmRoleMenuMapper jmRoleMenuMapper;

		 @Override
		 public JmRoleMenuDTO query(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.selectOne(jmRoleMenuDTO);
		 }
		 @Override
		 public List<JmRoleMenuDTO > queryList(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.selectAll(jmRoleMenuDTO);
		 }
		 @Override
		 public int add(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.insert(jmRoleMenuDTO);
		 }
		 @Override
		 public int modify(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.update(jmRoleMenuDTO);
		 }
		 @Override
		 public int delete(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.delete(jmRoleMenuDTO);
		 }

}

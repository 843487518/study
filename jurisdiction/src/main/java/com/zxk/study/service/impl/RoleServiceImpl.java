package com.zxk.study.service.impl;

import com.zxk.study.mapper.JmRoleMapper;
import com.zxk.study.module.dto.JmRoleDTO;
import com.zxk.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Service
public class RoleServiceImpl implements RoleService {

		 @Autowired
		 JmRoleMapper jmRoleMapper;

		 @Override
		 public JmRoleDTO query(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.selectOne(jmRoleDTO);
		 }
		 @Override
		 public List<JmRoleDTO > queryList(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.selectAll(jmRoleDTO);
		 }
		 @Override
		 public int add(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.insert(jmRoleDTO);
		 }
		 @Override
		 public int modify(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.update(jmRoleDTO);
		 }
		 @Override
		 public int delete(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.delete(jmRoleDTO);
		 }

}

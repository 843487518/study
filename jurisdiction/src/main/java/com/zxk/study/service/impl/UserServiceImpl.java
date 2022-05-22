package com.zxk.study.service.impl;

import com.zxk.study.mapper.JmRoleMapper;
import com.zxk.study.mapper.JmUserMapper;
import com.zxk.study.mapper.JmUserRoleMapper;
import com.zxk.study.module.bo.JwtUserBO;
import com.zxk.study.module.dto.JmMenuDTO;
import com.zxk.study.module.dto.JmRoleDTO;
import com.zxk.study.module.dto.JmUserDTO;
import com.zxk.study.module.dto.JmUserRoleDTO;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 JmUserMapper jmUserMapper;
		 @Autowired
		 JmRoleMapper jmRoleMapper;
		 @Autowired
		 JmUserRoleMapper jmUserRoleMapper;

		 @Override
		 public JmUserDTO query(JmUserDTO jmUserDTO){
		        return jmUserMapper.selectOne(jmUserDTO);
		 }
		 @Override
		 public List<JmUserDTO > queryList(JmUserDTO jmUserDTO){
		        return jmUserMapper.selectAll(jmUserDTO);
		 }
		 @Override
		 public int add(JmUserDTO jmUserDTO){
		        return jmUserMapper.insert(jmUserDTO);
		 }
		 @Override
		 public int modify(JmUserDTO jmUserDTO){
		        return jmUserMapper.update(jmUserDTO);
		 }
		 @Override
		 public int delete(JmUserDTO jmUserDTO){
		        return jmUserMapper.delete(jmUserDTO);
		 }
		@Override
		public BaseResult login(JmUserDTO jmUserDTO) {
			//1.判断数据库中是否存在当前用户
			JmUserDTO tbUser = jmUserMapper.selectOne(jmUserDTO);
			if (tbUser == null){
				return BaseResult.fail(BaseResultError.API_LOGIN_Fail_USER_NOT_EXISTED);
			}
			//2.判断密码是否匹配
			if (!(jmUserDTO.getPassword().equals(tbUser.getPassword()))){
				return BaseResult.fail(BaseResultError.API_LOGIN_Fail);
			}
			//3.查询账号对应的角色信息，下面生成Token时会用到
			JmUserRoleDTO sysUserRoleDTO = new JmUserRoleDTO();
			sysUserRoleDTO.setUserId(tbUser.getId());
			//通过账号id查对应的角色
			JmUserRoleDTO userRoleDTO = jmUserRoleMapper.selectOne(sysUserRoleDTO);
			JmRoleDTO jmRoleDTO = new JmRoleDTO();
			jmRoleDTO.setId(userRoleDTO.getRoleId());
			//通过角色id查角色信息
			JmRoleDTO roleDTO = jmRoleMapper.selectOne(jmRoleDTO);
			//4.构建Token包含的信息类
			JwtUserBO jwtUserBO = new JwtUserBO();
			jwtUserBO.setUserId(String.valueOf(tbUser.getId()));
			jwtUserBO.setUserName(tbUser.getUsername());
			jwtUserBO.setRoleId(userRoleDTO.getRoleId().toString());
			jwtUserBO.setRoleName(roleDTO.getRoleName());
			//5.生成Token,并返回给前端
			String token = JwtUtils.createToken(jwtUserBO);
			HashMap<String, Object> map = new HashMap<>();
			map.put("token",token);
			map.put("userid",tbUser.getId());
			map.put("roleid",userRoleDTO.getRoleId());
			List<JmMenuDTO> sysMenuDTOS = jmRoleMapper.selectMenu(jmRoleDTO);
			map.put("listmenu",sysMenuDTOS);
			return new BaseResult(BaseResultError.API_LOGIN_OK.getCode(),BaseResultError.API_LOGIN_OK.getMsg(),map);
		}

}

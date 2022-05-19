package com.zxk.study.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zxk.study.mapper.SysRoleMapper;
import com.zxk.study.mapper.SysUserRoleMapper;
import com.zxk.study.mapper.TbUserMapper;
import com.zxk.study.module.bo.JwtUserBO;
import com.zxk.study.module.dto.*;
import com.zxk.study.service.UserRoleService;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 TbUserMapper tbUserMapper;
		 @Autowired
		 SysUserRoleMapper sysUserRoleMapper;
		 @Autowired
		 SysRoleMapper sysRoleMapper;

		 @Override
		 public TbUserDTO query(TbUserDTO tbUserDTO){
		        return tbUserMapper.selectOne(tbUserDTO);
		 }
		 @Override
		 public List<TbUserDTO > queryList(TbUserDTO tbUserDTO){
		        return tbUserMapper.selectAll(tbUserDTO);
		 }
		 @Override
		 public int add(TbUserDTO tbUserDTO){
		        return tbUserMapper.insert(tbUserDTO);
		 }
		 @Override
		 public int modify(TbUserDTO tbUserDTO){
		        return tbUserMapper.update(tbUserDTO);
		 }
		 @Override
		 public int delete(TbUserDTO tbUserDTO){
		        return tbUserMapper.delete(tbUserDTO);
		 }


		@Override
		public Map login(TbUserDTO tbUserDTO) {
			//1.判断数据库中是否存在当前用户
			TbUserDTO tbUser = tbUserMapper.selectOne(tbUserDTO);
			if (tbUser == null){
				return null;
			}
			//2.判断密码是否匹配
			if (!(tbUserDTO.getPassword().equals(tbUser.getPassword()))){
				return null;
			}
			//3.查询账号对应的角色信息，下面生成Token时会用到
			SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
			sysUserRoleDTO.setUserId(tbUser.getUser_id());
			SysUserRoleDTO userRoleDTO = sysUserRoleMapper.selectOne(sysUserRoleDTO);
			SysRoleDTO sysRoleDTO = new SysRoleDTO();
			sysRoleDTO.setRole_id(userRoleDTO.getRoleId());
			SysRoleDTO roleDTO = sysRoleMapper.selectOne(sysRoleDTO);
			//4.构建Token包含的信息类
			JwtUserBO jwtUserBO = new JwtUserBO();
			jwtUserBO.setUserId(String.valueOf(tbUser.getUser_id()));
			jwtUserBO.setUserName(tbUser.getUsername());
			jwtUserBO.setRoleId(userRoleDTO.getRoleId().toString());
			jwtUserBO.setRoleName(roleDTO.getRoleName());
			//5.生成Token,并返回给前端
			String token = JwtUtils.createToken(jwtUserBO);
			HashMap<String, Object> map = new HashMap<>();
			map.put("token",token);
			map.put("userid",tbUser.getUser_id());
			map.put("roleid",userRoleDTO.getRoleId());
			List<SysMenuDTO> sysMenuDTOS = sysRoleMapper.selectMenu(sysRoleDTO);
			map.put("listmenu",sysMenuDTOS);
			return map;
		}

}

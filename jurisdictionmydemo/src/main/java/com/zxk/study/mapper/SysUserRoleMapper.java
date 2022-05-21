package com.zxk.study.mapper;


import com.zxk.study.module.dto.SysUserRoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@Mapper
public interface SysUserRoleMapper {

		 public SysUserRoleDTO selectOne(SysUserRoleDTO sysUserRoleDTO);
		 public List<SysUserRoleDTO > selectAll(SysUserRoleDTO sysUserRoleDTO);
		 public int insert(SysUserRoleDTO sysUserRoleDTO);
		 public int update(SysUserRoleDTO sysUserRoleDTO);
		 public int delete(SysUserRoleDTO sysUserRoleDTO);

}

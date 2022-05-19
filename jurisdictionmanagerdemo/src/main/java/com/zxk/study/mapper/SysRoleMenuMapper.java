package com.zxk.study.mapper;


import com.zxk.study.module.dto.SysRoleMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Mapper
public interface SysRoleMenuMapper {

		 public SysRoleMenuDTO selectOne(SysRoleMenuDTO sysRoleMenuDTO);
		 public List<SysRoleMenuDTO > selectAll(SysRoleMenuDTO sysRoleMenuDTO);
		 public int insert(SysRoleMenuDTO sysRoleMenuDTO);
		 public int update(SysRoleMenuDTO sysRoleMenuDTO);
		 public int delete(SysRoleMenuDTO sysRoleMenuDTO);

}

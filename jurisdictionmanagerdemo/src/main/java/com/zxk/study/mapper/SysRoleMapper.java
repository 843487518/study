package com.zxk.study.mapper;


import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.module.dto.SysRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Mapper
public interface SysRoleMapper {

		 public SysRoleDTO selectOne(SysRoleDTO sysRoleDTO);
		 public List<SysRoleDTO > selectAll(SysRoleDTO sysRoleDTO);
		 public int insert(SysRoleDTO sysRoleDTO);
		 public int update(SysRoleDTO sysRoleDTO);
		 public int delete(SysRoleDTO sysRoleDTO);
		 public List<SysMenuDTO> selectMenu(SysRoleDTO sysRoleDTO);

}

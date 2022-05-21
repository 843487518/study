package com.zxk.study.mapper;


import com.zxk.study.module.dto.SysMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Mapper
public interface SysMenuMapper {

		 public SysMenuDTO selectOne(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > selectAll(SysMenuDTO sysMenuDTO);
		 public int insert(SysMenuDTO sysMenuDTO);
		 public int update(SysMenuDTO sysMenuDTO);
		 public int delete(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > selectAllAll();

}

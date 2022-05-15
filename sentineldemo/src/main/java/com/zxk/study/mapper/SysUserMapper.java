package com.zxk.study.mapper;


import com.zxk.study.module.SysUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_user  用户表
* @author zhouxx
* @create	2022-04-26 16:39:26
*/
@Mapper
@Repository
public interface SysUserMapper {

		 public SysUserDTO selectOne(SysUserDTO sysUserDTO);
		 public List<SysUserDTO > selectAll(SysUserDTO sysUserDTO);
		 public int insert(SysUserDTO sysUserDTO);
		 public int update(SysUserDTO sysUserDTO);
		 public int delete(SysUserDTO sysUserDTO);

}

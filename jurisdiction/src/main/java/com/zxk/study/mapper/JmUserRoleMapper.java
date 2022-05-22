package com.zxk.study.mapper;

import com.zxk.study.module.dto.JmUserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Mapper
@Repository
public interface JmUserRoleMapper {

		 public JmUserRoleDTO selectOne(JmUserRoleDTO jmUserRoleDTO);
		 public List<JmUserRoleDTO > selectAll(JmUserRoleDTO jmUserRoleDTO);
		 public int insert(JmUserRoleDTO jmUserRoleDTO);
		 public int update(JmUserRoleDTO jmUserRoleDTO);
		 public int delete(JmUserRoleDTO jmUserRoleDTO);

}

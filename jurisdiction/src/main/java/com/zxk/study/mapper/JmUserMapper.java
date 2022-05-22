package com.zxk.study.mapper;


import com.zxk.study.module.dto.JmUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Mapper
@Repository
public interface JmUserMapper {

		 public JmUserDTO selectOne(JmUserDTO jmUserDTO);
		 public List<JmUserDTO > selectAll(JmUserDTO jmUserDTO);
		 public int insert(JmUserDTO jmUserDTO);
		 public int update(JmUserDTO jmUserDTO);
		 public int delete(JmUserDTO jmUserDTO);

}

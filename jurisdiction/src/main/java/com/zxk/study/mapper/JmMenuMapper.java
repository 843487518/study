package com.zxk.study.mapper;

import com.zxk.study.module.dto.JmMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Mapper
@Repository
public interface JmMenuMapper {

		 public JmMenuDTO selectOne(JmMenuDTO jmMenuDTO);
		 public List<JmMenuDTO > selectAll(JmMenuDTO jmMenuDTO);
		 public int insert(JmMenuDTO jmMenuDTO);
		 public int update(JmMenuDTO jmMenuDTO);
		 public int delete(JmMenuDTO jmMenuDTO);

}

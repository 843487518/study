package com.zxk.study.mapper;


import com.zxk.study.module.dto.TbUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@Mapper
public interface TbUserMapper {

		 public TbUserDTO selectOne(TbUserDTO tbUserDTO);
		 public List<TbUserDTO > selectAll(TbUserDTO tbUserDTO);
		 public int insert(TbUserDTO tbUserDTO);
		 public int update(TbUserDTO tbUserDTO);
		 public int delete(TbUserDTO tbUserDTO);

}

package com.zxk.study.mapper;


import com.zxk.study.module.dto.SysLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Mapper
public interface SysLogMapper {

		 public SysLogDTO selectOne(SysLogDTO sysLogDTO);
		 public List<SysLogDTO > selectAll(SysLogDTO sysLogDTO);
}

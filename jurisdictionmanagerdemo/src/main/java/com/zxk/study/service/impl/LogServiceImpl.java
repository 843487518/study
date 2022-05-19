package com.zxk.study.service.impl;

import java.util.List;
import com.zxk.study.mapper.SysLogMapper;
import com.zxk.study.module.dto.SysLogDTO;
import com.zxk.study.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Service
public class LogServiceImpl implements LogService {

		 @Autowired
		 SysLogMapper sysLogMapper;

		 @Override
		 public SysLogDTO query(SysLogDTO sysLogDTO){
		        return sysLogMapper.selectOne(sysLogDTO);
		 }
		 @Override
		 public List<SysLogDTO > queryList(SysLogDTO sysLogDTO){
		        return sysLogMapper.selectAll(sysLogDTO);
		 }
//		 public int add(SysLogDTO sysLogDTO){
//		        return sysLogMapper.insert(sysLogDTO);
//		 }
//		 public int modify(SysLogDTO sysLogDTO){
//		        return sysLogMapper.update(sysLogDTO);
//		 }
//		 public int delete(SysLogDTO sysLogDTO){
//		        return sysLogMapper.delete(sysLogDTO);
//		 }

}

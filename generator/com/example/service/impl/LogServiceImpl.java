package	com.example.service.impl;


import com.example.service.LogService;
import com.example.mapper.SysLogMapper;
import com.example.module.dto.SysLogDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class LogServiceImpl implements LogService {

		 @Autowired
		 SysLogMapper sysLogMapper;

		 public SysLogDTO query(SysLogDTO sysLogDTO){
		        return sysLogMapper.selectOne(sysLogDTO);
		 }
		 public List<SysLogDTO > queryList(SysLogDTO sysLogDTO){
		        return sysLogMapper.selectAll(sysLogDTO);
		 }
		 public int add(SysLogDTO sysLogDTO){
		        return sysLogMapper.insert(sysLogDTO);
		 }
		 public int modify(SysLogDTO sysLogDTO){
		        return sysLogMapper.update(sysLogDTO);
		 }
		 public int delete(SysLogDTO sysLogDTO){
		        return sysLogMapper.delete(sysLogDTO);
		 }

}

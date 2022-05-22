package	com.example.service;


import com.example.module.dto.SysLogDTO;
import java.util.List;


/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
public interface LogService {

		 public SysLogDTO query(SysLogDTO sysLogDTO);
		 public List<SysLogDTO > queryList(SysLogDTO sysLogDTO);
		 public int add(SysLogDTO sysLogDTO);
		 public int modify(SysLogDTO sysLogDTO);
		 public int delete(SysLogDTO sysLogDTO);

}

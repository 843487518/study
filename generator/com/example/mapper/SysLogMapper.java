package	com.example.mapper;


import com.example.module.dto.SysLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Mapper
@Repository
public interface SysLogMapper {

		 public SysLogDTO selectOne(SysLogDTO sysLogDTO);
		 public List<SysLogDTO > selectAll(SysLogDTO sysLogDTO);
		 public int insert(SysLogDTO sysLogDTO);
		 public int update(SysLogDTO sysLogDTO);
		 public int delete(SysLogDTO sysLogDTO);

}

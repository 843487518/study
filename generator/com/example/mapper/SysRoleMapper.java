package	com.example.mapper;


import com.example.module.dto.SysRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Mapper
@Repository
public interface SysRoleMapper {

		 public SysRoleDTO selectOne(SysRoleDTO sysRoleDTO);
		 public List<SysRoleDTO > selectAll(SysRoleDTO sysRoleDTO);
		 public int insert(SysRoleDTO sysRoleDTO);
		 public int update(SysRoleDTO sysRoleDTO);
		 public int delete(SysRoleDTO sysRoleDTO);

}

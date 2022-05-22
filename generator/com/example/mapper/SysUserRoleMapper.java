package	com.example.mapper;


import com.example.module.dto.SysUserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Mapper
@Repository
public interface SysUserRoleMapper {

		 public SysUserRoleDTO selectOne(SysUserRoleDTO sysUserRoleDTO);
		 public List<SysUserRoleDTO > selectAll(SysUserRoleDTO sysUserRoleDTO);
		 public int insert(SysUserRoleDTO sysUserRoleDTO);
		 public int update(SysUserRoleDTO sysUserRoleDTO);
		 public int delete(SysUserRoleDTO sysUserRoleDTO);

}

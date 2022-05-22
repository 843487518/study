package	com.example.service;


import com.example.module.dto.SysUserRoleDTO;
import java.util.List;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
public interface UserRoleService {

		 public SysUserRoleDTO query(SysUserRoleDTO sysUserRoleDTO);
		 public List<SysUserRoleDTO > queryList(SysUserRoleDTO sysUserRoleDTO);
		 public int add(SysUserRoleDTO sysUserRoleDTO);
		 public int modify(SysUserRoleDTO sysUserRoleDTO);
		 public int delete(SysUserRoleDTO sysUserRoleDTO);

}

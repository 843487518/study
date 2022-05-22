package	com.example.service;


import com.example.module.dto.SysRoleMenuDTO;
import java.util.List;


/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
public interface RoleMenuService {

		 public SysRoleMenuDTO query(SysRoleMenuDTO sysRoleMenuDTO);
		 public List<SysRoleMenuDTO > queryList(SysRoleMenuDTO sysRoleMenuDTO);
		 public int add(SysRoleMenuDTO sysRoleMenuDTO);
		 public int modify(SysRoleMenuDTO sysRoleMenuDTO);
		 public int delete(SysRoleMenuDTO sysRoleMenuDTO);

}

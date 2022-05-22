package	com.example.service;


import com.example.module.dto.RoleMenuDTO;
import java.util.List;


/**
* Table:role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
public interface MenuService {

		 public RoleMenuDTO query(RoleMenuDTO roleMenuDTO);
		 public List<RoleMenuDTO > queryList(RoleMenuDTO roleMenuDTO);
		 public int add(RoleMenuDTO roleMenuDTO);
		 public int modify(RoleMenuDTO roleMenuDTO);
		 public int delete(RoleMenuDTO roleMenuDTO);

}

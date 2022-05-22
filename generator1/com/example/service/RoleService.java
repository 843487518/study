package	com.example.service;


import com.example.module.dto.UserRoleDTO;
import java.util.List;


/**
* Table:user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
public interface RoleService {

		 public UserRoleDTO query(UserRoleDTO userRoleDTO);
		 public List<UserRoleDTO > queryList(UserRoleDTO userRoleDTO);
		 public int add(UserRoleDTO userRoleDTO);
		 public int modify(UserRoleDTO userRoleDTO);
		 public int delete(UserRoleDTO userRoleDTO);

}

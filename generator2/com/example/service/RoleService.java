package	com.example.service;


import com.example.module.dto.JmRoleDTO;
import java.util.List;


/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
public interface RoleService {

		 public JmRoleDTO query(JmRoleDTO jmRoleDTO);
		 public List<JmRoleDTO > queryList(JmRoleDTO jmRoleDTO);
		 public int add(JmRoleDTO jmRoleDTO);
		 public int modify(JmRoleDTO jmRoleDTO);
		 public int delete(JmRoleDTO jmRoleDTO);

}

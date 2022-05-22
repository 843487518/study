package	com.example.service.impl;


import com.example.service.RoleMenuService;
import com.example.mapper.JmRoleMenuMapper;
import com.example.module.dto.JmRoleMenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:jm_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

		 @Autowired
		 JmRoleMenuMapper jmRoleMenuMapper;

		 public JmRoleMenuDTO query(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.selectOne(jmRoleMenuDTO);
		 }
		 public List<JmRoleMenuDTO > queryList(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.selectAll(jmRoleMenuDTO);
		 }
		 public int add(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.insert(jmRoleMenuDTO);
		 }
		 public int modify(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.update(jmRoleMenuDTO);
		 }
		 public int delete(JmRoleMenuDTO jmRoleMenuDTO){
		        return jmRoleMenuMapper.delete(jmRoleMenuDTO);
		 }

}

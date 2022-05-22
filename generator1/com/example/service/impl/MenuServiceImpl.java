package	com.example.service.impl;


import com.example.service.MenuService;
import com.example.mapper.RoleMenuMapper;
import com.example.module.dto.RoleMenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Service
public class MenuServiceImpl implements MenuService {

		 @Autowired
		 RoleMenuMapper roleMenuMapper;

		 public RoleMenuDTO query(RoleMenuDTO roleMenuDTO){
		        return roleMenuMapper.selectOne(roleMenuDTO);
		 }
		 public List<RoleMenuDTO > queryList(RoleMenuDTO roleMenuDTO){
		        return roleMenuMapper.selectAll(roleMenuDTO);
		 }
		 public int add(RoleMenuDTO roleMenuDTO){
		        return roleMenuMapper.insert(roleMenuDTO);
		 }
		 public int modify(RoleMenuDTO roleMenuDTO){
		        return roleMenuMapper.update(roleMenuDTO);
		 }
		 public int delete(RoleMenuDTO roleMenuDTO){
		        return roleMenuMapper.delete(roleMenuDTO);
		 }

}

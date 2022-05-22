package	com.example.service.impl;


import com.example.service.RoleService;
import com.example.mapper.UserRoleMapper;
import com.example.module.dto.UserRoleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Service
public class RoleServiceImpl implements RoleService {

		 @Autowired
		 UserRoleMapper userRoleMapper;

		 public UserRoleDTO query(UserRoleDTO userRoleDTO){
		        return userRoleMapper.selectOne(userRoleDTO);
		 }
		 public List<UserRoleDTO > queryList(UserRoleDTO userRoleDTO){
		        return userRoleMapper.selectAll(userRoleDTO);
		 }
		 public int add(UserRoleDTO userRoleDTO){
		        return userRoleMapper.insert(userRoleDTO);
		 }
		 public int modify(UserRoleDTO userRoleDTO){
		        return userRoleMapper.update(userRoleDTO);
		 }
		 public int delete(UserRoleDTO userRoleDTO){
		        return userRoleMapper.delete(userRoleDTO);
		 }

}

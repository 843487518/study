package	com.example.service.impl;


import com.example.service.UserRoleService;
import com.example.mapper.JmUserRoleMapper;
import com.example.module.dto.JmUserRoleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

		 @Autowired
		 JmUserRoleMapper jmUserRoleMapper;

		 public JmUserRoleDTO query(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.selectOne(jmUserRoleDTO);
		 }
		 public List<JmUserRoleDTO > queryList(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.selectAll(jmUserRoleDTO);
		 }
		 public int add(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.insert(jmUserRoleDTO);
		 }
		 public int modify(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.update(jmUserRoleDTO);
		 }
		 public int delete(JmUserRoleDTO jmUserRoleDTO){
		        return jmUserRoleMapper.delete(jmUserRoleDTO);
		 }

}

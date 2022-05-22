package	com.example.service.impl;


import com.example.service.RoleService;
import com.example.mapper.JmRoleMapper;
import com.example.module.dto.JmRoleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Service
public class RoleServiceImpl implements RoleService {

		 @Autowired
		 JmRoleMapper jmRoleMapper;

		 public JmRoleDTO query(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.selectOne(jmRoleDTO);
		 }
		 public List<JmRoleDTO > queryList(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.selectAll(jmRoleDTO);
		 }
		 public int add(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.insert(jmRoleDTO);
		 }
		 public int modify(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.update(jmRoleDTO);
		 }
		 public int delete(JmRoleDTO jmRoleDTO){
		        return jmRoleMapper.delete(jmRoleDTO);
		 }

}

package	com.example.service.impl;


import com.example.service.RoleService;
import com.example.mapper.SysRoleMapper;
import com.example.module.dto.SysRoleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class RoleServiceImpl implements RoleService {

		 @Autowired
		 SysRoleMapper sysRoleMapper;

		 public SysRoleDTO query(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.selectOne(sysRoleDTO);
		 }
		 public List<SysRoleDTO > queryList(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.selectAll(sysRoleDTO);
		 }
		 public int add(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.insert(sysRoleDTO);
		 }
		 public int modify(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.update(sysRoleDTO);
		 }
		 public int delete(SysRoleDTO sysRoleDTO){
		        return sysRoleMapper.delete(sysRoleDTO);
		 }

}

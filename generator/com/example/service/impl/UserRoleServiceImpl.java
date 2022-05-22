package	com.example.service.impl;


import com.example.service.UserRoleService;
import com.example.mapper.SysUserRoleMapper;
import com.example.module.dto.SysUserRoleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

		 @Autowired
		 SysUserRoleMapper sysUserRoleMapper;

		 public SysUserRoleDTO query(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.selectOne(sysUserRoleDTO);
		 }
		 public List<SysUserRoleDTO > queryList(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.selectAll(sysUserRoleDTO);
		 }
		 public int add(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.insert(sysUserRoleDTO);
		 }
		 public int modify(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.update(sysUserRoleDTO);
		 }
		 public int delete(SysUserRoleDTO sysUserRoleDTO){
		        return sysUserRoleMapper.delete(sysUserRoleDTO);
		 }

}

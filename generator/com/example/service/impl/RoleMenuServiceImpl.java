package	com.example.service.impl;


import com.example.service.RoleMenuService;
import com.example.mapper.SysRoleMenuMapper;
import com.example.module.dto.SysRoleMenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

		 @Autowired
		 SysRoleMenuMapper sysRoleMenuMapper;

		 public SysRoleMenuDTO query(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.selectOne(sysRoleMenuDTO);
		 }
		 public List<SysRoleMenuDTO > queryList(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.selectAll(sysRoleMenuDTO);
		 }
		 public int add(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.insert(sysRoleMenuDTO);
		 }
		 public int modify(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.update(sysRoleMenuDTO);
		 }
		 public int delete(SysRoleMenuDTO sysRoleMenuDTO){
		        return sysRoleMenuMapper.delete(sysRoleMenuDTO);
		 }

}

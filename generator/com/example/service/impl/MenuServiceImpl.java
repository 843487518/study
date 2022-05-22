package	com.example.service.impl;


import com.example.service.MenuService;
import com.example.mapper.SysMenuMapper;
import com.example.module.dto.SysMenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class MenuServiceImpl implements MenuService {

		 @Autowired
		 SysMenuMapper sysMenuMapper;

		 public SysMenuDTO query(SysMenuDTO sysMenuDTO){
		        return sysMenuMapper.selectOne(sysMenuDTO);
		 }
		 public List<SysMenuDTO > queryList(SysMenuDTO sysMenuDTO){
		        return sysMenuMapper.selectAll(sysMenuDTO);
		 }
		 public int add(SysMenuDTO sysMenuDTO){
		        return sysMenuMapper.insert(sysMenuDTO);
		 }
		 public int modify(SysMenuDTO sysMenuDTO){
		        return sysMenuMapper.update(sysMenuDTO);
		 }
		 public int delete(SysMenuDTO sysMenuDTO){
		        return sysMenuMapper.delete(sysMenuDTO);
		 }

}

package	com.example.service;


import com.example.module.dto.SysMenuDTO;
import java.util.List;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
public interface MenuService {

		 public SysMenuDTO query(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > queryList(SysMenuDTO sysMenuDTO);
		 public int add(SysMenuDTO sysMenuDTO);
		 public int modify(SysMenuDTO sysMenuDTO);
		 public int delete(SysMenuDTO sysMenuDTO);

}

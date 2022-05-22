package	com.example.mapper;


import com.example.module.dto.SysMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Mapper
@Repository
public interface SysMenuMapper {

		 public SysMenuDTO selectOne(SysMenuDTO sysMenuDTO);
		 public List<SysMenuDTO > selectAll(SysMenuDTO sysMenuDTO);
		 public int insert(SysMenuDTO sysMenuDTO);
		 public int update(SysMenuDTO sysMenuDTO);
		 public int delete(SysMenuDTO sysMenuDTO);

}

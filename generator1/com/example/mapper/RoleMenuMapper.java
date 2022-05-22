package	com.example.mapper;


import com.example.module.dto.RoleMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Mapper
@Repository
public interface RoleMenuMapper {

		 public RoleMenuDTO selectOne(RoleMenuDTO roleMenuDTO);
		 public List<RoleMenuDTO > selectAll(RoleMenuDTO roleMenuDTO);
		 public int insert(RoleMenuDTO roleMenuDTO);
		 public int update(RoleMenuDTO roleMenuDTO);
		 public int delete(RoleMenuDTO roleMenuDTO);

}

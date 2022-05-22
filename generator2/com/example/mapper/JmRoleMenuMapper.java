package	com.example.mapper;


import com.example.module.dto.JmRoleMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:jm_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Mapper
@Repository
public interface JmRoleMenuMapper {

		 public JmRoleMenuDTO selectOne(JmRoleMenuDTO jmRoleMenuDTO);
		 public List<JmRoleMenuDTO > selectAll(JmRoleMenuDTO jmRoleMenuDTO);
		 public int insert(JmRoleMenuDTO jmRoleMenuDTO);
		 public int update(JmRoleMenuDTO jmRoleMenuDTO);
		 public int delete(JmRoleMenuDTO jmRoleMenuDTO);

}

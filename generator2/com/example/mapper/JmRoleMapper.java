package	com.example.mapper;


import com.example.module.dto.JmRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Mapper
@Repository
public interface JmRoleMapper {

		 public JmRoleDTO selectOne(JmRoleDTO jmRoleDTO);
		 public List<JmRoleDTO > selectAll(JmRoleDTO jmRoleDTO);
		 public int insert(JmRoleDTO jmRoleDTO);
		 public int update(JmRoleDTO jmRoleDTO);
		 public int delete(JmRoleDTO jmRoleDTO);

}

package	com.example.mapper;


import com.example.module.dto.UserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Mapper
@Repository
public interface UserRoleMapper {

		 public UserRoleDTO selectOne(UserRoleDTO userRoleDTO);
		 public List<UserRoleDTO > selectAll(UserRoleDTO userRoleDTO);
		 public int insert(UserRoleDTO userRoleDTO);
		 public int update(UserRoleDTO userRoleDTO);
		 public int delete(UserRoleDTO userRoleDTO);

}

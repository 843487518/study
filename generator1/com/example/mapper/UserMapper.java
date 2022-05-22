package	com.example.mapper;


import com.example.module.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:user  用户
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Mapper
@Repository
public interface UserMapper {

		 public UserDTO selectOne(UserDTO userDTO);
		 public List<UserDTO > selectAll(UserDTO userDTO);
		 public int insert(UserDTO userDTO);
		 public int update(UserDTO userDTO);
		 public int delete(UserDTO userDTO);

}

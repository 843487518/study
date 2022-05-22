package	com.example.service;


import com.example.module.dto.UserDTO;
import java.util.List;


/**
* Table:user  用户
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
public interface UserService {

		 public UserDTO query(UserDTO userDTO);
		 public List<UserDTO > queryList(UserDTO userDTO);
		 public int add(UserDTO userDTO);
		 public int modify(UserDTO userDTO);
		 public int delete(UserDTO userDTO);

}

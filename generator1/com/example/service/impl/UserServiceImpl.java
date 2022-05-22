package	com.example.service.impl;


import com.example.service.UserService;
import com.example.mapper.UserMapper;
import com.example.module.dto.UserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:user  用户
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 UserMapper userMapper;

		 public UserDTO query(UserDTO userDTO){
		        return userMapper.selectOne(userDTO);
		 }
		 public List<UserDTO > queryList(UserDTO userDTO){
		        return userMapper.selectAll(userDTO);
		 }
		 public int add(UserDTO userDTO){
		        return userMapper.insert(userDTO);
		 }
		 public int modify(UserDTO userDTO){
		        return userMapper.update(userDTO);
		 }
		 public int delete(UserDTO userDTO){
		        return userMapper.delete(userDTO);
		 }

}

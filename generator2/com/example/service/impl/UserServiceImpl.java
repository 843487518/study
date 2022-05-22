package	com.example.service.impl;


import com.example.service.UserService;
import com.example.mapper.JmUserMapper;
import com.example.module.dto.JmUserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 JmUserMapper jmUserMapper;

		 public JmUserDTO query(JmUserDTO jmUserDTO){
		        return jmUserMapper.selectOne(jmUserDTO);
		 }
		 public List<JmUserDTO > queryList(JmUserDTO jmUserDTO){
		        return jmUserMapper.selectAll(jmUserDTO);
		 }
		 public int add(JmUserDTO jmUserDTO){
		        return jmUserMapper.insert(jmUserDTO);
		 }
		 public int modify(JmUserDTO jmUserDTO){
		        return jmUserMapper.update(jmUserDTO);
		 }
		 public int delete(JmUserDTO jmUserDTO){
		        return jmUserMapper.delete(jmUserDTO);
		 }

}

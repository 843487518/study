package	com.example.service;


import com.example.module.dto.JmUserDTO;
import java.util.List;


/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
public interface UserService {

		 public JmUserDTO query(JmUserDTO jmUserDTO);
		 public List<JmUserDTO > queryList(JmUserDTO jmUserDTO);
		 public int add(JmUserDTO jmUserDTO);
		 public int modify(JmUserDTO jmUserDTO);
		 public int delete(JmUserDTO jmUserDTO);

}

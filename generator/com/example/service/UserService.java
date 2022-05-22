package	com.example.service;


import com.example.module.dto.TbUserDTO;
import java.util.List;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
public interface UserService {

		 public TbUserDTO query(TbUserDTO tbUserDTO);
		 public List<TbUserDTO > queryList(TbUserDTO tbUserDTO);
		 public int add(TbUserDTO tbUserDTO);
		 public int modify(TbUserDTO tbUserDTO);
		 public int delete(TbUserDTO tbUserDTO);

}

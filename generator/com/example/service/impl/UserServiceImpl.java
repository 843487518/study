package	com.example.service.impl;


import com.example.service.UserService;
import com.example.mapper.TbUserMapper;
import com.example.module.dto.TbUserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Service
public class UserServiceImpl implements UserService {

		 @Autowired
		 TbUserMapper tbUserMapper;

		 public TbUserDTO query(TbUserDTO tbUserDTO){
		        return tbUserMapper.selectOne(tbUserDTO);
		 }
		 public List<TbUserDTO > queryList(TbUserDTO tbUserDTO){
		        return tbUserMapper.selectAll(tbUserDTO);
		 }
		 public int add(TbUserDTO tbUserDTO){
		        return tbUserMapper.insert(tbUserDTO);
		 }
		 public int modify(TbUserDTO tbUserDTO){
		        return tbUserMapper.update(tbUserDTO);
		 }
		 public int delete(TbUserDTO tbUserDTO){
		        return tbUserMapper.delete(tbUserDTO);
		 }

}

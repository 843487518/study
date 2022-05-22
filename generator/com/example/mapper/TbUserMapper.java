package	com.example.mapper;


import com.example.module.dto.TbUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Mapper
@Repository
public interface TbUserMapper {

		 public TbUserDTO selectOne(TbUserDTO tbUserDTO);
		 public List<TbUserDTO > selectAll(TbUserDTO tbUserDTO);
		 public int insert(TbUserDTO tbUserDTO);
		 public int update(TbUserDTO tbUserDTO);
		 public int delete(TbUserDTO tbUserDTO);

}

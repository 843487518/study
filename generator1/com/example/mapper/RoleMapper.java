package	com.example.mapper;


import com.example.module.dto.RoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:role  角色
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Mapper
@Repository
public interface RoleMapper {

		 public RoleDTO selectOne(RoleDTO roleDTO);
		 public List<RoleDTO > selectAll(RoleDTO roleDTO);
		 public int insert(RoleDTO roleDTO);
		 public int update(RoleDTO roleDTO);
		 public int delete(RoleDTO roleDTO);

}

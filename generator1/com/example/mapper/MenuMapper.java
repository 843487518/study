package	com.example.mapper;


import com.example.module.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
* Table:menu  菜单管理
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@Mapper
@Repository
public interface MenuMapper {

		 public MenuDTO selectOne(MenuDTO menuDTO);
		 public List<MenuDTO > selectAll(MenuDTO menuDTO);
		 public int insert(MenuDTO menuDTO);
		 public int update(MenuDTO menuDTO);
		 public int delete(MenuDTO menuDTO);

}

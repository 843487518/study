package	com.example.service.impl;


import com.example.service.MenuService;
import com.example.mapper.JmMenuMapper;
import com.example.module.dto.JmMenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@Service
public class MenuServiceImpl implements MenuService {

		 @Autowired
		 JmMenuMapper jmMenuMapper;

		 public JmMenuDTO query(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.selectOne(jmMenuDTO);
		 }
		 public List<JmMenuDTO > queryList(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.selectAll(jmMenuDTO);
		 }
		 public int add(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.insert(jmMenuDTO);
		 }
		 public int modify(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.update(jmMenuDTO);
		 }
		 public int delete(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.delete(jmMenuDTO);
		 }

}

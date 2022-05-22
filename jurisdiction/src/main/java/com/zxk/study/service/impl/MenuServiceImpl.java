package com.zxk.study.service.impl;

import com.zxk.study.mapper.JmMenuMapper;
import com.zxk.study.module.dto.JmMenuDTO;
import com.zxk.study.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@Service
public class MenuServiceImpl implements MenuService {

		 @Autowired
		 JmMenuMapper jmMenuMapper;

		 @Override
		 public JmMenuDTO query(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.selectOne(jmMenuDTO);
		 }
		 @Override
		 public List<JmMenuDTO > queryList(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.selectAll(jmMenuDTO);
		 }
		 @Override
		 public int add(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.insert(jmMenuDTO);
		 }
		 @Override
		 public int modify(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.update(jmMenuDTO);
		 }
		 @Override
		 public int delete(JmMenuDTO jmMenuDTO){
		        return jmMenuMapper.delete(jmMenuDTO);
		 }

}

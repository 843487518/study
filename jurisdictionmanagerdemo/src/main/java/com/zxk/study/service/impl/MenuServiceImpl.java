package com.zxk.study.service.impl;

import java.util.List;
import com.zxk.study.mapper.SysMenuMapper;
import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	 @Override
	 public SysMenuDTO query(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.selectOne(sysMenuDTO);
	 }
	 @Override
	 public List<SysMenuDTO > queryList(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.selectAll(sysMenuDTO);
	 }
	 @Override
	 public int add(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.insert(sysMenuDTO);
	 }
	 @Override
	 public int modify(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.update(sysMenuDTO);
	 }
	 @Override
	 public int delete(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.delete(sysMenuDTO);
	 }

}

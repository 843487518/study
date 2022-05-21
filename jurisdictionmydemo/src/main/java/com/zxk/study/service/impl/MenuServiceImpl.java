package com.zxk.study.service.impl;

import com.zxk.study.mapper.SysMenuMapper;
import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.service.MenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	 public BaseResult add(SysMenuDTO sysMenuDTO){
		 int insert = sysMenuMapper.insert(sysMenuDTO);
		 if(insert==1){
		 	return BaseResult.success("");
		 }
		 return BaseResult.fail(BaseResultError.API_DO_FAIL);
	 }
	 @Override
	 public int modify(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.update(sysMenuDTO);
	 }
	 @Override
	 public int delete(SysMenuDTO sysMenuDTO){
	        return sysMenuMapper.delete(sysMenuDTO);
	 }

	@Override
	public List<SysMenuDTO> queryListAll() {return sysMenuMapper.selectAllAll(); }

}

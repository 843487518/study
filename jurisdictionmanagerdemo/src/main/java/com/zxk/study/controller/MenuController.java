package com.zxk.study.controller;

import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.module.vo.input.MenuInput;
import com.zxk.study.service.MenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/menu")
public class MenuController {

		 @Autowired
		 MenuService menuService;
		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        sysMenuDTO.setMenu_id(id);
		        return BaseResult.success(menuService.query(sysMenuDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody MenuInput menuInput){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(menuService.queryList(sysMenuDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody MenuInput menuInput){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
		        int cnt= menuService.add(sysMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody MenuInput menuInput){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
		        int cnt= menuService.modify(sysMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        sysMenuDTO.setMenu_id(id);
		        int cnt= menuService.delete(sysMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

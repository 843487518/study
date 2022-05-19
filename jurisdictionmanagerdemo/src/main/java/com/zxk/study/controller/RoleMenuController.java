package com.zxk.study.controller;

import com.zxk.study.module.dto.SysRoleMenuDTO;
import com.zxk.study.module.vo.input.RoleMenuInput;
import com.zxk.study.service.RoleMenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/roleMenu")
public class RoleMenuController {

		 @Autowired
		 RoleMenuService roleMenuService;

		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        sysRoleMenuDTO.setId(id); 
		        return BaseResult.success(roleMenuService.query(sysRoleMenuDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(roleMenuService.queryList(sysRoleMenuDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        int cnt= roleMenuService.add(sysRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        int cnt= roleMenuService.modify(sysRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        sysRoleMenuDTO.setId(id); 
		        int cnt= roleMenuService.delete(sysRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

package com.zxk.study.controller;

import com.zxk.study.module.dto.JmRoleMenuDTO;
import com.zxk.study.module.vo.input.RoleMenuInput;
import com.zxk.study.service.RoleMenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
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
		        JmRoleMenuDTO jmRoleMenuDTO=new JmRoleMenuDTO();
		        jmRoleMenuDTO.setId(id); 
		        return BaseResult.success(roleMenuService.query(jmRoleMenuDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody RoleMenuInput roleMenuInput){
		        JmRoleMenuDTO jmRoleMenuDTO=new JmRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, jmRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(roleMenuService.queryList(jmRoleMenuDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody RoleMenuInput roleMenuInput){
		        JmRoleMenuDTO jmRoleMenuDTO=new JmRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, jmRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= roleMenuService.add(jmRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody RoleMenuInput roleMenuInput){
		        JmRoleMenuDTO jmRoleMenuDTO=new JmRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, jmRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleMenuDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= roleMenuService.modify(jmRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        JmRoleMenuDTO jmRoleMenuDTO=new JmRoleMenuDTO();
		        jmRoleMenuDTO.setId(id); 
		        int cnt= roleMenuService.delete(jmRoleMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

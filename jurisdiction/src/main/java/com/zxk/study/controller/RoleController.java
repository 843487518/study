package com.zxk.study.controller;

import com.zxk.study.module.dto.JmRoleDTO;
import com.zxk.study.module.vo.input.RoleInput;
import com.zxk.study.service.RoleService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_role  角色
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/role")
public class RoleController {

		 @Autowired
		 RoleService roleService;

		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        JmRoleDTO jmRoleDTO=new JmRoleDTO();
		        jmRoleDTO.setId(id); 
		        return BaseResult.success(roleService.query(jmRoleDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody RoleInput roleInput){
		        JmRoleDTO jmRoleDTO=new JmRoleDTO();
		        BeanUtils.copyProperties(roleInput, jmRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(roleService.queryList(jmRoleDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody RoleInput roleInput){
		        JmRoleDTO jmRoleDTO=new JmRoleDTO();
		        BeanUtils.copyProperties(roleInput, jmRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= roleService.add(jmRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody RoleInput roleInput){
		        JmRoleDTO jmRoleDTO=new JmRoleDTO();
		        BeanUtils.copyProperties(roleInput, jmRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmRoleDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= roleService.modify(jmRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        JmRoleDTO jmRoleDTO=new JmRoleDTO();
		        jmRoleDTO.setId(id); 
		        int cnt= roleService.delete(jmRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

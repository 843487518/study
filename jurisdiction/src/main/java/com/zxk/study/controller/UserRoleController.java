package com.zxk.study.controller;

import com.zxk.study.module.dto.JmUserRoleDTO;
import com.zxk.study.module.vo.input.UserRoleInput;
import com.zxk.study.service.UserRoleService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/userRole")
public class UserRoleController {

		 @Autowired
		 UserRoleService userRoleService;

		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        jmUserRoleDTO.setId(id); 
		        return BaseResult.success(userRoleService.query(jmUserRoleDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(userRoleService.queryList(jmUserRoleDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userRoleService.add(jmUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserRoleDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userRoleService.modify(jmUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        jmUserRoleDTO.setId(id); 
		        int cnt= userRoleService.delete(jmUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

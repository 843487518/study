package com.zxk.study.controller;

import com.zxk.study.module.dto.SysUserRoleDTO;
import com.zxk.study.module.vo.input.UserRoleInput;
import com.zxk.study.service.UserRoleService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-17 20:35:39
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
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        sysUserRoleDTO.setId(id); 
		        return BaseResult.success(userRoleService.query(sysUserRoleDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(userRoleService.queryList(sysUserRoleDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        int cnt= userRoleService.add(sysUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        int cnt= userRoleService.modify(sysUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        sysUserRoleDTO.setId(id); 
		        int cnt= userRoleService.delete(sysUserRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

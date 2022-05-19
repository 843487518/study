package com.zxk.study.controller;



import com.zxk.study.module.dto.SysRoleDTO;
import com.zxk.study.module.vo.input.RoleInput;
import com.zxk.study.service.RoleService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-17 20:35:39
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
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        sysRoleDTO.setRole_id(id);
		        return BaseResult.success(roleService.query(sysRoleDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(roleService.queryList(sysRoleDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        int cnt= roleService.add(sysRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        int cnt= roleService.modify(sysRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        sysRoleDTO.setRole_id(id);
		        int cnt= roleService.delete(sysRoleDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

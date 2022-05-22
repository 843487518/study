package	com.example.controller;


import com.example.module.vo.input.UserRoleInput;
import com.example.service.UserRoleService;
import com.example.module.dto.SysUserRoleDTO;
import com.zxx.study.web.util.PassToken;
import com.zxx.study.web.module.ApiResult;
import com.zxx.study.web.util.DateUtils;
import com.zxx.study.web.common.commenum.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/userRole")
public class UserRoleController {

		 @Autowired
		 UserRoleService userRoleService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        sysUserRoleDTO.setId(id); 
		        return ApiResult.success(userRoleService.query(sysUserRoleDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(userRoleService.queryList(sysUserRoleDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userRoleService.add(sysUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody UserRoleInput userRoleInput){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, sysUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        sysUserRoleDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userRoleService.modify(sysUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
		        sysUserRoleDTO.setId(id); 
		        int cnt= userRoleService.delete(sysUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

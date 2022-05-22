package	com.example.controller;


import com.example.module.vo.input.RoleInput;
import com.example.service.RoleService;
import com.example.module.dto.SysRoleDTO;
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
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/role")
public class RoleController {

		 @Autowired
		 RoleService roleService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        sysRoleDTO.setId(id); 
		        return ApiResult.success(roleService.query(sysRoleDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(roleService.queryList(sysRoleDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= roleService.add(sysRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody RoleInput roleInput){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        BeanUtils.copyProperties(roleInput, sysRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        sysRoleDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= roleService.modify(sysRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        SysRoleDTO sysRoleDTO=new SysRoleDTO();
		        sysRoleDTO.setId(id); 
		        int cnt= roleService.delete(sysRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

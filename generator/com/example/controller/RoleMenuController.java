package	com.example.controller;


import com.example.module.vo.input.RoleMenuInput;
import com.example.service.RoleMenuService;
import com.example.module.dto.SysRoleMenuDTO;
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
* Table:sys_role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/roleMenu")
public class RoleMenuController {

		 @Autowired
		 RoleMenuService roleMenuService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        sysRoleMenuDTO.setId(id); 
		        return ApiResult.success(roleMenuService.query(sysRoleMenuDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(roleMenuService.queryList(sysRoleMenuDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysRoleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= roleMenuService.add(sysRoleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody RoleMenuInput roleMenuInput){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        BeanUtils.copyProperties(roleMenuInput, sysRoleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        sysRoleMenuDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= roleMenuService.modify(sysRoleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        SysRoleMenuDTO sysRoleMenuDTO=new SysRoleMenuDTO();
		        sysRoleMenuDTO.setId(id); 
		        int cnt= roleMenuService.delete(sysRoleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

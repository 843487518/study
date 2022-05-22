package	com.example.controller;


import com.example.module.vo.input.UserRoleInput;
import com.example.service.UserRoleService;
import com.example.module.dto.JmUserRoleDTO;
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
* Table:jm_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-22 20:20:49
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
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        jmUserRoleDTO.setId(id); 
		        return ApiResult.success(userRoleService.query(jmUserRoleDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(userRoleService.queryList(jmUserRoleDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserRoleDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userRoleService.add(jmUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody UserRoleInput userRoleInput){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        BeanUtils.copyProperties(userRoleInput, jmUserRoleDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        jmUserRoleDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userRoleService.modify(jmUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        JmUserRoleDTO jmUserRoleDTO=new JmUserRoleDTO();
		        jmUserRoleDTO.setId(id); 
		        int cnt= userRoleService.delete(jmUserRoleDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

package	com.example.controller;


import com.example.module.vo.input.MenuInput;
import com.example.service.MenuService;
import com.example.module.dto.RoleMenuDTO;
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
* Table:role_menu  角色与菜单对应关系
* @author zhouxx
* @create	2022-05-22 17:45:58
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/menu")
public class MenuController {

		 @Autowired
		 MenuService menuService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        RoleMenuDTO roleMenuDTO=new RoleMenuDTO();
		        roleMenuDTO.setId(id); 
		        return ApiResult.success(menuService.query(roleMenuDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody MenuInput menuInput){
		        RoleMenuDTO roleMenuDTO=new RoleMenuDTO();
		        BeanUtils.copyProperties(menuInput, roleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //roleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(menuService.queryList(roleMenuDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody MenuInput menuInput){
		        RoleMenuDTO roleMenuDTO=new RoleMenuDTO();
		        BeanUtils.copyProperties(menuInput, roleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //roleMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= menuService.add(roleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody MenuInput menuInput){
		        RoleMenuDTO roleMenuDTO=new RoleMenuDTO();
		        BeanUtils.copyProperties(menuInput, roleMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        roleMenuDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= menuService.modify(roleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        RoleMenuDTO roleMenuDTO=new RoleMenuDTO();
		        roleMenuDTO.setId(id); 
		        int cnt= menuService.delete(roleMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

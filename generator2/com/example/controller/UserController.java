package	com.example.controller;


import com.example.module.vo.input.UserInput;
import com.example.service.UserService;
import com.example.module.dto.JmUserDTO;
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
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

		 @Autowired
		 UserService userService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        jmUserDTO.setId(id); 
		        return ApiResult.success(userService.query(jmUserDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(userService.queryList(jmUserDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userService.add(jmUserDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        jmUserDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userService.modify(jmUserDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        jmUserDTO.setId(id); 
		        int cnt= userService.delete(jmUserDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

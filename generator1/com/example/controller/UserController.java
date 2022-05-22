package	com.example.controller;


import com.example.module.vo.input.UserInput;
import com.example.service.UserService;
import com.example.module.dto.UserDTO;
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
* Table:user  用户
* @author zhouxx
* @create	2022-05-22 17:45:58
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
		        UserDTO userDTO=new UserDTO();
		        userDTO.setId(id); 
		        return ApiResult.success(userService.query(userDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody UserInput userInput){
		        UserDTO userDTO=new UserDTO();
		        BeanUtils.copyProperties(userInput, userDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //userDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(userService.queryList(userDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody UserInput userInput){
		        UserDTO userDTO=new UserDTO();
		        BeanUtils.copyProperties(userInput, userDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //userDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userService.add(userDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody UserInput userInput){
		        UserDTO userDTO=new UserDTO();
		        BeanUtils.copyProperties(userInput, userDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        userDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userService.modify(userDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        UserDTO userDTO=new UserDTO();
		        userDTO.setId(id); 
		        int cnt= userService.delete(userDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

package com.zxk.study.controller;

import com.zxk.study.module.dto.JmUserDTO;
import com.zxk.study.module.vo.input.UserInput;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_user  用户
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

		 @Autowired
		 UserService userService;

		 //用户登录接口
		@RequestMapping("/login")
		public BaseResult login(Model model, @RequestParam String userName, @RequestParam String pwd){
			JmUserDTO tbUserDTO=new JmUserDTO();
			tbUserDTO.setUsername(userName);
			tbUserDTO.setPassword(pwd);
			//调用service层的登录处理逻辑
			return userService.login(tbUserDTO);

		}


		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        jmUserDTO.setId(id); 
		        return BaseResult.success(userService.query(jmUserDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(userService.queryList(jmUserDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= userService.add(jmUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody UserInput userInput){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        BeanUtils.copyProperties(userInput, jmUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmUserDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= userService.modify(jmUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        JmUserDTO jmUserDTO=new JmUserDTO();
		        jmUserDTO.setId(id); 
		        int cnt= userService.delete(jmUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

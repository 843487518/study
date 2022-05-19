package com.zxk.study.controller;


import java.util.List;
import java.util.Map;

import com.zxk.study.module.dto.TbUserDTO;
import com.zxk.study.module.vo.input.UserInput;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

		 @Autowired
		 UserService userService;

		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        TbUserDTO tbUserDTO=new TbUserDTO();
		        tbUserDTO.setUser_id(id);
		        return BaseResult.success(userService.query(tbUserDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody UserInput userInput){
		        TbUserDTO tbUserDTO=new TbUserDTO();
		        BeanUtils.copyProperties(userInput, tbUserDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //tbUserDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(userService.queryList(tbUserDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody UserInput userInput){
		        TbUserDTO tbUserDTO=new TbUserDTO();
		        BeanUtils.copyProperties(userInput, tbUserDTO);
		        int cnt= userService.add(tbUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody UserInput userInput){
		        TbUserDTO tbUserDTO=new TbUserDTO();
		        BeanUtils.copyProperties(userInput, tbUserDTO);
		        int cnt= userService.modify(tbUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        TbUserDTO tbUserDTO=new TbUserDTO();
		        tbUserDTO.setUser_id(id);
		        int cnt= userService.delete(tbUserDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
	@PassToken
	@GetMapping("/login")
	public BaseResult login(@RequestParam("username") @NotNull(message = "用户名不能为空") String username,
							@RequestParam("pwd")@NotNull(message = "密码不能为空") String pwd){
		TbUserDTO tbUserDTO=new TbUserDTO();
		tbUserDTO.setUsername(username);
		tbUserDTO.setPassword(pwd);
		//调用service层的登录处理逻辑
		Map login = userService.login(tbUserDTO);
		//判断失败的返回值
		if(login == null){
			return BaseResult.fail(BaseResultError.API_DO_FAIL);
		}
		return BaseResult.success(login);
	}

}

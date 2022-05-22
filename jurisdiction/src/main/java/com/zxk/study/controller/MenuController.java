package com.zxk.study.controller;

import com.zxk.study.module.dto.JmMenuDTO;
import com.zxk.study.module.vo.input.MenuInput;
import com.zxk.study.service.MenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 16:56:27
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/menu")
public class MenuController {

		 @Autowired
		 MenuService menuService;

		 @GetMapping("/detail/{id}")
		 public BaseResult getDetail(@PathVariable("id") long id){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        jmMenuDTO.setId(id); 
		        return BaseResult.success(menuService.query(jmMenuDTO));
		 }
		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(menuService.queryList(jmMenuDTO));
		 }
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= menuService.add(jmMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmMenuDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= menuService.modify(jmMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public BaseResult delete(@PathVariable("id") long id){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        jmMenuDTO.setId(id); 
		        int cnt= menuService.delete(jmMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

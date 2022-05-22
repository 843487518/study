package com.zxk.study.controller;

import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.module.vo.input.MenuInput;
import com.zxk.study.service.MenuService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Controller
@Slf4j
@Validated
@RequestMapping("/api/v1/menu")
public class MenuController {

		 @Autowired
		 MenuService menuService;
		 @PassToken
		 @GetMapping("/detail")
		 @ResponseBody
		 public BaseResult getDetail(@RequestParam long id){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        sysMenuDTO.setMenuId(id);
		        return BaseResult.success(menuService.query(sysMenuDTO));
		 }
		 @PassToken
		 @GetMapping("/listmenu")
		 public String getMenuList(HttpServletRequest request){
			 List<SysMenuDTO> sysMenuDTOS = menuService.queryListAll();
			 BaseResult baseResult = BaseResult.success(sysMenuDTOS);
			 request.getSession().setAttribute("listBaseResult",baseResult);
			 return "menu";
		 }

		 @PostMapping("/list")
		 public BaseResult getList(@RequestBody MenuInput menuInput){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return BaseResult.success(menuService.queryList(sysMenuDTO));
		 }
		 @PassToken
		 @PostMapping("/listThymeleaf")
		 public ModelAndView getListThymeleaf(@RequestBody MenuInput menuInput){
			 SysMenuDTO sysMenuDTO=new SysMenuDTO();
			 BeanUtils.copyProperties(menuInput, sysMenuDTO);
			 List<SysMenuDTO> sysMenuDTOS = menuService.queryList(sysMenuDTO);
			 ModelAndView modelAndView = new ModelAndView("/thymeleaf/menu");
			 modelAndView.getModel().put("menuList",sysMenuDTOS);
			 return modelAndView;
		 }
		 @PassToken
		 @ResponseBody
		 @PostMapping("/add")
		 public BaseResult add(@RequestBody MenuInput menuInput,HttpServletRequest request){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
			 	BaseResult add = menuService.add(sysMenuDTO);
//			 	request.getSession().setAttribute("addBaseResult",add);
			 	return add;


		 }
		 @PassToken
		 @ResponseBody
		 @PostMapping("/modify")
		 public BaseResult modify(@RequestBody MenuInput menuInput){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        BeanUtils.copyProperties(menuInput, sysMenuDTO);
		        BaseResult modify = menuService.modify(sysMenuDTO);
			 	return modify;

		 }
		 @PassToken
		 @GetMapping("/delete/{id}")
		 @ResponseBody
		 public BaseResult delete(@PathVariable("id") long id){
		        SysMenuDTO sysMenuDTO=new SysMenuDTO();
		        sysMenuDTO.setMenuId(id);
		        int cnt= menuService.delete(sysMenuDTO);
		        if(cnt==1){
		            return BaseResult.success(cnt);
		        }
		        return BaseResult.fail(BaseResultError.API_DO_FAIL);
		 }

}

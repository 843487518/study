package com.zxk.study.controller;

import com.zxk.study.module.dto.SysMenuDTO;
import com.zxk.study.module.dto.TbUserDTO;
import com.zxk.study.service.UserService;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/20  18:56
 */

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PassToken
    @RequestMapping("/login")
    public String login(Model model, @RequestParam String name, @RequestParam String password){
        TbUserDTO tbUserDTO=new TbUserDTO();
        tbUserDTO.setUsername(name);
        tbUserDTO.setPassword(password);
        //调用service层的登录处理逻辑
        BaseResult login = userService.login(tbUserDTO);
        model.addAttribute("BaseResult",login);
        return "home";
    }
}

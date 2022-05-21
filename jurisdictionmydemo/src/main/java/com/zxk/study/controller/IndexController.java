package com.zxk.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/20  17:14
 */

@Controller
//@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}

package com.zxk.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/20  12:56
 */
@Controller
@ResponseBody
public class IndexController {

    @RequestMapping("/")
    public String index(){

        return "index";
    }

    @RequestMapping("/index1")
    public String index1(){

        return "index1";
    }

}

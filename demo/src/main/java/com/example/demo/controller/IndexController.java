package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/20  14:03
 */
@Controller
@ResponseBody
public class IndexController {

    @RequestMapping("index")
    public String  index(){

        return "index";
    }
}

package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/30  13:24
 */
@RestController
@RequestMapping("/api/v1/user")
public class IndexController {
    @GetMapping("/one/{id}")
    public String getUserOne(@PathVariable("id") long id){
        return new String("myid"+id);
    }

}

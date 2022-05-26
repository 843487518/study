package com.zxk.study.controller;

import com.zxk.study.utils.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/24  21:52
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1")
public class EnterpriseController {

    @GetMapping("/attendance/{id}")
    public BaseResult getDetail(@PathVariable("id") long id){
        System.out.println(id+"请求了接口");
        return BaseResult.success("打卡成功了呀！！！");
    }
}

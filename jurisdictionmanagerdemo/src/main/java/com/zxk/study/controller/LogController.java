//package com.zxk.study.controller;
//
//
//import java.util.List;
//
//import com.zxk.study.module.dto.SysLogDTO;
//import com.zxk.study.service.LogService;
//import com.zxk.study.utils.BaseResult;
//import com.zxk.study.utils.BaseResultError;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//
///**
//* Table:sys_log  系统日志
//* @author zhouxx
//* @create	2022-05-17 20:35:39
//*/
//@RestController
//@Slf4j
//@Validated
//@RequestMapping("/api/v1/log")
//public class LogController {
//
//		 @Autowired
//		 LogService logService;
//
//		 @GetMapping("/detail/{id}")
//		 public BaseResult getDetail(@PathVariable("id") long id){
//		        SysLogDTO sysLogDTO=new SysLogDTO();
//		        sysLogDTO.setId(id);
//		        return BaseResult.success(logService.query(sysLogDTO));
//		 }
//		 @PostMapping("/list")
//		 public BaseResult getList(@RequestBody LogInput logInput){
//		        SysLogDTO sysLogDTO=new SysLogDTO();
//		        BeanUtils.copyProperties(logInput, sysLogDTO);
//		        //TODO 如果有日期类型，记得要转换一下！！！
//		        //sysLogDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
//		        return BaseResult.success(logService.queryList(sysLogDTO));
//		 }
//
//}

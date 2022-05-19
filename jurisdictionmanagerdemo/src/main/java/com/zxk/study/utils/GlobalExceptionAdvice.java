package com.zxk.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/4/24  9:07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(JwtException.class)
    public Object exception(HttpServletRequest request, JwtException e){
        //将真实的异常信息写入日志
        log.error(e.getMessage());
        //给用户返回错误信息
        return BaseResult.fail(e);
    }
    @ExceptionHandler(Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, Exception e){
        //将真实的异常信息写入日志
        log.error(e.getMessage());
//        //对真实的异常信息进行处理
//        String[] split = e.getMessage().toString().split(" ");
//        String s = split[split.length - 1].toString();
//        String replace = s.replace("[", " ");
//        String replace1 = replace.replace("]", " ");
//        String trim = replace1.trim();
//        log.info(trim);
//        //封装一个返还给用户的异常信息
//        TheException theException = new TheException();
//        theException.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        theException.setMessage(trim);
//        theException.setUrl(request.getRequestURI().toString());
        //给用户返回错误信息
        return BaseResult.fail(BaseResultError.API_NETWORK_ERROR);
    }
}

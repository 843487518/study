package com.zxk.study.utils;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhouxx
 * @Description:
 * @date 2022/3/3  13:21
 */
@Data
@ToString
public class BaseResult<T> implements Serializable {
    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    private BaseResult(){
    }
    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static BaseResult success(Object data){
        return new BaseResult(BaseResultError.SUCCESS.getCode(),BaseResultError.SUCCESS.getMsg(),data);
    }
//    public static BaseResult fail(JwtException e) {
//
//        return new BaseResult(e.getCode(),e.getExceptionMessage(),null);
//    }
    public static BaseResult fail(BaseResultError resultError) {

        return new BaseResult(resultError.getCode(),resultError.getMsg(),null);
    }
    public static BaseResult fail(BaseResultError resultError,Object data) {

        return new BaseResult(resultError.getCode(),resultError.getMsg(),data);
    }
    public static BaseResult fail(int code,String msg){

        return new BaseResult(code,msg,null);
    }
}

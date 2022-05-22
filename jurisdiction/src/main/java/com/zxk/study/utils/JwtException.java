package com.zxk.study.utils;

public class JwtException extends RuntimeException {
    int code;
    String exceptionMessage;

    private JwtException(){
    }
    public JwtException(BaseResultError error){
       this(error.getCode(),error.getMsg());
    }
    public JwtException(int code, String msg){
        super(msg);
        this.code=code;
        this.exceptionMessage=msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

package com.zxk.study.utils;

public enum BaseResultError {


    //前端调用错误码（用户操作错误码）
    SUCCESS(0, "成功!"),
    API_OK(0, "成功!"),
    API_INVALID_PARAMETER(1000, "输入有误，请重新输入！"),
    API_NO_PARAMETER(1000, "输入有误，请重新输入！"),
    API_FUNCTION_NO_ACCESS(1001, "无访问权限，请联系管理人员！"),
    API_UNKNOWN_ERROR(1002, "网络超时，请稍后操作！"),
    API_NETWORK_ERROR(1003, "网络异常，请稍后操作！"),
    API_DO_FAIL(1004, "操作失败！"),


    /**  100-199  鉴权错误码 */
    API_LOGIN_OK(0, "登录成功!"),
    API_REGISTER_OK(0,"注册成功！"),
    API_LOGIN_Fail(100, "登录失败，账号或密码错误!"),
    API_LOGIN_Fail_USER_NOT_EXISTED(101, "登录失败，该账户未注册，请重新注册!"),
    API_LOGIN_NO_TOKEN(102, "缺少token，请重新登录!"),
    API_LOGIN_INVALID_TOKEN(103, "无效token，请重新登录!"),
    API_REGISTER_USER_EXIST(104,"账号已存在，请勿重复注册！")
    ;

    private String msg;
    private int code;

    private BaseResultError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}

package com.zxk.study.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * @author zhouxx
 * @create 2022-04-08 22:00
 */
@Component
public class Constants {
    public static final String LOG_MDC_ID = "trace_id";
    public static final String DOT = ".";

    /** OPTIONS：返回服务器针对特定资源所支持的 HTTP 请求方法，也可以利用其向 web 服务器发送 * 的请求来测试服务器的功能性。*/
    public static final String HTTP_OPTIONS = "OPTIONS";
    /** HEAD：向服务器索与 GET 请求相一致的响应，只不过响应体将不会被返回。这一方法可以在不必传输整个响应内容的情况下，就可以获取包含在响应小消息头中的元信息。*/
    public static final String HTTP_HEAD = "HEAD";
    /** GET：向特定的资源发出请求，它的本质就是发送一个请求来取得服务器上的某一资源。*/
    public static final String HTTP_GET = "GET";
    /** POST：向指定资源提交数据进行处理请求（例如提交表单或者上传文件等）。数据被包含在请求体中。*/
    public static final String HTTP_POST = "POST";
    /** PUT：向指定资源位置上传最新内容。*/
    public static final String HTTP_PUT = "PUT";
    /** DELETE：请求服务器删除 Request-URL 所表示的资源。*/
    public static final String HTTP_DELETE = "DELETE";
    /** TRACE：回显服务器收到的请求，主要用于测试或诊断。*/
    public static final String HTTP_TRACE = "TRACE";
    /** CONNECT：协议中预留给能够将连接改为管道方式的代理服务器。*/
    public static final String HTTP_CONNECT = "CONNECT";

    public static  ObjectMapper jsonMapper=new ObjectMapper();


}

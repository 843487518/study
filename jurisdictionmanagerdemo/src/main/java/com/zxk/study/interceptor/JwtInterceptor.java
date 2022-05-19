package com.zxk.study.interceptor;

import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.JwtException;
import com.zxk.study.utils.JwtUtils;
import com.zxk.study.utils.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zhouxx
 * @create 2022-04-10 10:07
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }


        //从 http 请求头中取出 token
        String token = request.getHeader("token");
        log.info("token：" + token);

        if (token == null) {
            throw new JwtException(BaseResultError.API_LOGIN_NO_TOKEN);
        }

        /**
         *
         * 令牌组成：
         *      标头（Header）
         *      有效载荷（Payload）
         *      签名（Signature）
         * token格式：head.payload.singurater 如：xxxxx.yyyy.zzzz
         *
         * Header：有令牌的类型和所使用的签名算法，如HMAC、SHA256、RSA；使用Base64编码组成；（Base64是一种编码，不是一种加密过程，可以被翻译成原来的样子）
         *
         * base64UrlEncode{
         * 	"alg" : "HS256",
         * 	"type" : "JWT"
         * }
         * Payload ：有效负载，包含声明；声明是有关实体（通常是用户）和其他数据的声明，不放用户敏感的信息，如密码。同样使用Base64编码
         *
         * base64UrlEncode{
         * 	"sub" : "123",
         * 	"name" : "John Do",
         * 	"admin" : true
         * }
         * Signature ：前面两部分都使用Base64进行编码，前端可以解开知道里面的信息。Signature需要使用编码后的header和payload
         * 加上我们提供的一个密钥，使用header中指定的签名算法(HS256)进行签名。签名的作用是保证JWT没有被篡改过
         * HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret);
         *
         * **签名目的：**签名的过程实际上是对头部以及负载内容进行签名，防止内容被窜改。如果有人对头部以及负载的内容解码之后进行修改，再进行编码，最后加上之前的签名组合形成新的JWT的话，那么服务器端会判断出新的头部和负载形成的签名和JWT附带上的签名是不一样的。如果要对新的头部和负载进行签名，在不知道服务器加密时用的密钥的话，得出来的签名也是不一样的。
         *
         * 信息安全问题：Base64是一种编码，是可逆的，适合传递一些非敏感信息；JWT中不应该在负载中加入敏感的数据。如传输用户的ID被知道也是安全的，如密码不能放在JWT中；JWT常用于设计用户认证、授权系统、web的单点登录。
         * HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret);
         *
         * 常见异常信息
         * SignatureVerificationException //签名不一致异常
         * TokenExpiredException //令牌过期异常
         * AlgorithmMismatchException //算法不匹配异常
         * InvalidClaimException //失效的payload异常（传给客户端后，token被改动，验证不一致）
         *
         * */
//        try{
//            JwtUtils.verifyToken(token);//验证令牌
//            return true;//放行请求
//        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
//            map.put("msg","无效签名");
//        } catch (TokenExpiredException e) {
//            e.printStackTrace();
//            map.put("msg","token过期");
//        } catch (AlgorithmMismatchException e) {
//            e.printStackTrace();
//            map.put("msg","token算法不一致");
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("msg","token失效");
//        }
//        map.put("state",false);//设置状态
//        //将map转化成json，response使用的是Jackson
//        String json = new ObjectMapper().writeValueAsString(map);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().print(json);
//        return false;

        //验证 token
        boolean checkResult= JwtUtils.verifyToken(token);
        if(checkResult){
            //验证通过后， 这里测试取出JWT中存放的数据
            //获取 token 中的 userId
            String Audience = JwtUtils.getAudience(token);
            log.info("token-Audience：" + Audience);
            //获取 token 中的其他数据
            String userId = JwtUtils.getClaimByName(token,"userId").asString();
            String userName = JwtUtils.getClaimByName(token,"userName").asString();
            log.info("token-userId：{}, userName：{}", userId,userName);

            String secretToken = JwtUtils.getClaimByName(token,"secretToken").asString();
            log.info("token-secretToken：{}" , secretToken);
            // 校验真正的token=secretToken

            return true;
        }else {
            throw new JwtException(BaseResultError.API_LOGIN_INVALID_TOKEN);
        }
    }
}

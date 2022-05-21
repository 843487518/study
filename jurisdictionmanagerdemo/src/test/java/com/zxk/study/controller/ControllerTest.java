package com.zxk.study.controller;

import com.zxk.study.module.vo.input.MenuInput;
import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
import com.zxk.study.utils.Constants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/19  13:43
 */

@SpringBootTest
@Slf4j
public class ControllerTest {

    @Autowired
    UserController userController;


    //登录测试
    //正例
    @Test
    void login(){
      BaseResult login = userController.login("zhouxk","123456");
      //断言
        Assert.isTrue(BaseResultError.SUCCESS.getCode()==login.getCode(),"登录成功");
//        Assert.isTrue(BaseResultError.SUCCESS.getCode()==login.getData().,"登录成功");

    }
    //用户不存在
    @Test
    void login1(){
        BaseResult login1 = userController.login("123","456");
        Assert.isTrue(BaseResultError.API_DO_FAIL.getCode()==login1.getCode(),"用户不存在");
    }
    //输入数据存在空值
    @Test
    void login2(){
        try{
            BaseResult login2 = userController.login(null,null);
        }catch (Exception e){
            Assert.isTrue(e.getClass().equals(NullPointerException.class),"登录失败");
        }
    }

    //输入数据存在空值
    @Test
    void login3(){
            BaseResult login3= userController.login(null,null);
    }

    @Autowired
    MenuController menuController;
    @Test
    void menu(){
        BaseResult baseResult= menuController.getDetail(1L);
        log.info("{}",baseResult);
        Assert.isTrue(BaseResultError.SUCCESS.getCode()==baseResult.getCode(),"获取菜单详情");

    }
    //登录
    @Test
    void loginTest(){
        RestTemplate restTemplate=new RestTemplate();
        // header填充
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        String loginUrl="http://192.168.1.45:8081/api/v1/user/login?username=zhouxk&pwd=123456";
        HttpEntity<String> request = new HttpEntity<String>("", headers);
// 一个单例的restTemplate
//        RestTemplate restTemplate = HttpInvoker.getRestTemplate();
// 发送请求
        ResponseEntity<BaseResult> response = restTemplate.getForEntity(loginUrl, BaseResult.class);

        log.info("response={}",response.getBody());
    }
    //菜单查询
    @Test
    void menuTest(){
        RestTemplate restTemplate=new RestTemplate();
        // header填充
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.put("token", Collections.singletonList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicm9sZUlkIjoiMSIsInNlY3JldFRva2VuIjoidXNlcklkI3VzZXJOYW1lI3JvbGVJZCNleHBpcmVzRGF0ZSNNRDUiLCJyb2xlTmFtZSI6IuS4u-euoSIsImV4cCI6MTY1Mjk2MjQ1MCwidXNlck5hbWUiOiJ6aG91eGsiLCJpYXQiOjE2NTI5NTg4NTAsInVzZXJJZCI6IjEifQ.KhjAfoHh4yOcpfvGaawoi6bxCTUNo7El8lvmRqiZ0CY"));
// body填充
        HttpEntity<String> request = new HttpEntity<String>("", headers);
// 发送请求
        String menuDetailUrl="http://192.168.1.45:8081/api/v1/menu/detail/1";
//        ResponseEntity<BaseResult> response = restTemplate.getForEntity(menuDetailUrl,BaseResult.class);
        ResponseEntity<BaseResult> response = restTemplate.exchange(menuDetailUrl, HttpMethod.GET, request, BaseResult.class);
        log.info("response={}",response.getBody());
    }

    //查询查询多个菜单
    @SneakyThrows
    @Test
    void menuFindListTest(){
        RestTemplate restTemplate=new RestTemplate();
        // header填充
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.put("token", Collections.singletonList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicm9sZUlkIjoiMSIsInNlY3JldFRva2VuIjoidXNlcklkI3VzZXJOYW1lI3JvbGVJZCNleHBpcmVzRGF0ZSNNRDUiLCJyb2xlTmFtZSI6IuS4u-euoSIsImV4cCI6MTY1MzAxMzk4NywidXNlck5hbWUiOiJ6aG91eGsiLCJpYXQiOjE2NTMwMTAzODcsInVzZXJJZCI6IjEifQ.ZGKtszT23WaxRqBEfexdnKG_lb410AjJcaNtcLXlpz4"));
        // body填充
        LinkedMultiValueMap<String, String> bodys = new LinkedMultiValueMap<>();
        MenuInput menuInput = MenuInput.builder()
                .deleteFlag(0)
                .build();
        String menuToString = Constants.jsonMapper.writeValueAsString(menuInput);
        bodys.add("menuInput",menuToString);

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(bodys, headers);
        // 发送请求
        String menuDetailUrl="http://192.168.1.45:8081/api/v1/menu/list";
        ResponseEntity<BaseResult> response = restTemplate.exchange(menuDetailUrl, HttpMethod.POST, request, BaseResult.class);
        log.info("response={}",response.getBody());
    }
    //角色查询
    @Test
    void roleTest(){
        RestTemplate restTemplate=new RestTemplate();
        // header填充
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.put("token", Collections.singletonList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicm9sZUlkIjoiMSIsInNlY3JldFRva2VuIjoidXNlcklkI3VzZXJOYW1lI3JvbGVJZCNleHBpcmVzRGF0ZSNNRDUiLCJyb2xlTmFtZSI6IuS4u-euoSIsImV4cCI6MTY1MzAxMDQyNSwidXNlck5hbWUiOiJ6aG91eGsiLCJpYXQiOjE2NTMwMDY4MjUsInVzZXJJZCI6IjEifQ.xC0htYvH8UHtnGNdey9BrAacO7Wg2CUuMrIk13YiCMw"));
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        //发送请求
        String menuDetailUrl="http://192.168.1.45:8081/api/v1/role/detail/1";
        ResponseEntity<BaseResult> response = restTemplate.exchange(menuDetailUrl, HttpMethod.GET, request, BaseResult.class);
        log.info("response={}",response.getBody());
    }
}

package com.zxk.study.controller;

import com.zxk.study.utils.BaseResult;
import com.zxk.study.utils.BaseResultError;
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
    @Test
    void menuTest(){
        RestTemplate restTemplate=new RestTemplate();

        //BaseResult(code=0, msg=成功!, data={roleid=1, userid=1, listmenu=[{menu_id=1, parentId=0, name=权限管理, url=qwe, icon=null, createUserId=1, createTime=null, updateTime=null, deleteFlag=null}, {menu_id=2, parentId=0, name=菜单管理, url=1111, icon=null, createUserId=1, createTime=null, updateTime=null, deleteFlag=null}, {menu_id=3, parentId=0, name=角色管理, url=2222, icon=null, createUserId=1, createTime=null, updateTime=null, deleteFlag=null}], token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicm9sZUlkIjoiMSIsInNlY3JldFRva2VuIjoidXNlcklkI3VzZXJOYW1lI3JvbGVJZCNleHBpcmVzRGF0ZSNNRDUiLCJyb2xlTmFtZSI6IuS4u-euoSIsImV4cCI6MTY1Mjk2MjQ1MCwidXNlck5hbWUiOiJ6aG91eGsiLCJpYXQiOjE2NTI5NTg4NTAsInVzZXJJZCI6IjEifQ.KhjAfoHh4yOcpfvGaawoi6bxCTUNo7El8lvmRqiZ0CY})


        // header填充
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.put("token", Collections.singletonList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwicm9sZUlkIjoiMSIsInNlY3JldFRva2VuIjoidXNlcklkI3VzZXJOYW1lI3JvbGVJZCNleHBpcmVzRGF0ZSNNRDUiLCJyb2xlTmFtZSI6IuS4u-euoSIsImV4cCI6MTY1Mjk2MjQ1MCwidXNlck5hbWUiOiJ6aG91eGsiLCJpYXQiOjE2NTI5NTg4NTAsInVzZXJJZCI6IjEifQ.KhjAfoHh4yOcpfvGaawoi6bxCTUNo7El8lvmRqiZ0CY"));


// body填充
//        JSONObject json = new JSONObject();
//        json.put("oldMobile", mobile);
//        json.put("newMobile", form.getNewMobile());
        HttpEntity<String> request = new HttpEntity<String>("", headers);
// 一个单例的restTemplate
//        RestTemplate restTemplate = HttpInvoker.getRestTemplate();
// 发送请求
        String menuDetailUrl="http://192.168.1.45:8081/api/v1/menu/detail/1";
//        ResponseEntity<BaseResult> response = restTemplate.getForEntity(menuDetailUrl,BaseResult.class);

        ResponseEntity<BaseResult> response = restTemplate.exchange(menuDetailUrl, HttpMethod.GET, request, BaseResult.class);


        log.info("response={}",response.getBody());
    }
}

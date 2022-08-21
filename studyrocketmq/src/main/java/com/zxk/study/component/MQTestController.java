package com.zxk.study.component;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/6/4  14:20
 */
@Controller
@RequestMapping("/MQTest")
public class MQTestController {

    private final String topic = "TestTopic";
    @Resource
    private SpringBootProducer producer;
    @ResponseBody
    @RequestMapping("/sendMessage/{msg}")
    public String sendMessage(@PathVariable("msg") String message) {
        producer.sendMessage(topic, message);
        return "消息发送完成";
    }
    @SneakyThrows
    @ResponseBody
    @RequestMapping("/sendMessage/{msg}/{pgi}")
    public String sendMessage(@PathVariable("msg") String message,@PathVariable("pgi") String pgi) {

        return MyDefaulMQProducer.sendMessage(pgi, message);
    }


    //这个发送事务消息的例子中有很多问题，需要注意下。
    @RequestMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }
}


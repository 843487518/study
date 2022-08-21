//package com.zxk.study.component;
//
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author Zhouxinkai
// * @Description:
// * @date 2022/6/4  14:18
// */
//@Component
//@RocketMQMessageListener(consumerGroup = "springBootGroup", topic = "TestTopic")
//public class SpringBootConsumer2 implements RocketMQListener {
//
//    @Override
//    public void onMessage(Object message) {
//        System.out.println("消费者2"+"Received message : "+ message);
//    }
//}
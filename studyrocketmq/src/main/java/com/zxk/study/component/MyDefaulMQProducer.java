package com.zxk.study.component;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhouxinkai
 * @Description:使用DefaultMQProducer发送消息
 * @date 2022/6/4  15:37
 */
public class MyDefaulMQProducer {

    private static DefaultMQProducer producer1 = new DefaultMQProducer("group_test1");
    public static String sendMessage(String producerGroup,String message)  {
        try {
            producer1.setProducerGroup(producerGroup);
            producer1.setNamesrvAddr("192.168.1.7:9876");
            producer1.start();
            //构建消息
            Message message1 = new Message("TestTopic", "TestTAG1", message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = producer1.send(message1);
            return String.valueOf(send.getSendStatus());
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
       finally {
            producer1.shutdown();
        }
    }


}

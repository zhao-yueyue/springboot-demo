package com.ml.common.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

//@Component
//@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiver {
    @RabbitHandler
    public void process(Map<String,String> testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.get("messageData"));
        /*try {
            String ss = null;
            ss.toString();
        }catch (Exception e){
            e.fillInStackTrace();
            System.out.println("DirectReceiver消费者收到消息  : 抛个异常玩玩！");
        }*/
    }
}

//package com.jbyf.rabbitmq_5.framework.simple;
//
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.MessageProperties;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class MQListener {
//    @RabbitListener(
//            queuesToDeclare = @Queue("spring.queue.simple")
////            ,
////            ackMode = "MANUAL"
////            ackMode = "AUTO"
////            ackMode = "NONE"
//    )
//    public void simpleListener(String msg, Message message ,Channel channel) throws IOException {
//        try {
//            int i = 1/0;
//            System.out.println("msg = " + msg);
//            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        }catch (Exception e){
//            //这是第一次出现异常
//            if(message.getMessageProperties().getRedelivered()){
//                //拒绝消息,不再重新入队
//                System.out.println("消息重试后依然失败，拒绝再次接收");
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
//            }else {
//                System.out.println(" 重新加入队列");
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//            }
//        }
//    }
//}

package com.jbyf.rabbitmq_5.framework.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class MQListener2 {
    //一个@RabbitListener代表一个消费者,可以在注解中声明交换机和队列,简化代码
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("spring.queue.topic"),
                    exchange = @Exchange(type = ExchangeTypes.TOPIC,name = "spring.exchange.topic"),
                    key = {"*.*"}//routeingkey
            )
    )
    public void topicConsumer(String msg, Channel channel, Message message){
        try {
            System.out.println("接收到消息" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.jbyf.rabbitmq_5.framework.producertoack;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ProducerAutoAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //先行初始化rebbitTemplate
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //确认消息是否到达交换机
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息发送成功");
        }else {
            System.out.println("消息发送失败" + cause + " 数据:" +correlationData);
        }
    }

    //消息如果没有发送到队列时的回调
    @Override
    public void returnedMessage(Message message,
                                int replyCode,
                                String replyText,
                                String exchange,
                                String routingKey) {
        System.out.println("消息主体: " + new String(message.getBody()));
        System.out.println("应答码: " + replyCode);
        System.out.println("描述: " + replyText);
        System.out.println("使用的交换机: " + exchange);
        System.out.println("使用的 routeKey: " + routingKey);

    }
}

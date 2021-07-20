package com.jbyf.rabbitmq_5.origin.fanout;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import javax.swing.*;
import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        final String QUEUE_NAME = RabbitMQUtils.BASIC_QUEUE_NAME + "fanout1" ;
        final String EXCHANGE_NAME = "fanout" + RabbitMQUtils.BASIC_EXCHANGE_NAME;

        //通道绑定对应的消息队列
        //参数1 : queue       队列名称    如果队列不存在则自动创建
        //参数2 : durable     定义队列特性是否要持久化    如果没有设置持久化,rabbit服务重启时会销毁队列,队列中的消息也会丢失,如果要保证消息的持久化,需要在发布消息时添加额外的设置
        //参数3 : exclusive   是否独占队列--当前队列是否只为本链接使用
        //参数4 : autoDelete  是否在消费完成后自动删除队列
        //参数5 : 额外参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //给通道绑定队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: " + new String(body));
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}

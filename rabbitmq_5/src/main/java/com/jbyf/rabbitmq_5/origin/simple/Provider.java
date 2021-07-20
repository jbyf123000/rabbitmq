package com.jbyf.rabbitmq_5.origin.simple;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

//简单模型不需要交换机
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //设置队列
        channel.queueDeclare("simple-queue",false,false,false,null);
        //设置信息
        String msg = "___woshinidie+";

        //发布消息
        //参数1 : exchange    交换机名称
        //参数2 : 队列名称
        //参数3 : 传递消息的额外设置   可以在此处设置队列消息持久化  MessageProperties.PERSISTENT_TEXT_PLAIN:持久化消息
        //参数4 : 消息的具体内容
        channel.basicPublish("","simple-queue",null,msg.getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

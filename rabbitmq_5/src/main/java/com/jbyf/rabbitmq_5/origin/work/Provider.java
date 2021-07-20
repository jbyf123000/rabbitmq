package com.jbyf.rabbitmq_5.origin.work;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    //两个人同时订阅一个队列
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare("work-queue",false,false,false,null);

        //发送的消息
        for (int i = 0; i < 50; i++) {
            String basicMsg = "当我第  " + i + "  次 给你妈发消息";
            channel.basicPublish("","work-queue",false,null,basicMsg.getBytes());
        }


        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}

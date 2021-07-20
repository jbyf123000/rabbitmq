package com.jbyf.rabbitmq_5.origin.direct;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        final String EXCHANGE_NAME = "direct" + RabbitMQUtils.BASIC_EXCHANGE_NAME;

        //声明exchange ,指定类型为 direct
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 消息内容
        String message = " woshinidie ";

        //吧消息发送到交换机
//        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"select",null,message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

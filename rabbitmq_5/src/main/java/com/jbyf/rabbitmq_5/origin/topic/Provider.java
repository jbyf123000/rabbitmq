package com.jbyf.rabbitmq_5.origin.topic;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        final String TYPE = "topic";
        final String EXCHANGE_NAME = TYPE + RabbitMQUtils.BASIC_EXCHANGE_NAME;

        //创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME,TYPE);

        //发送的消息
        String msg = " woshinidie ";
        channel.basicPublish(EXCHANGE_NAME,"lazy.msg",null , msg.getBytes());
//        channel.basicPublish(EXCHANGE_NAME,"jbyf.woshinidie.sb",null , msg.getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}

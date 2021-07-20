package com.jbyf.rabbitmq_5.origin.fanout;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        final String TYPE = "fanout";
        final String EXCHANGE_NAME = TYPE + RabbitMQUtils.BASIC_EXCHANGE_NAME;

        //创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME,TYPE);
        for (int i = 0; i < 50; i++) {
            //消息内容
            String msg = " woshinidie " + i;

            //发送消息
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        }

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

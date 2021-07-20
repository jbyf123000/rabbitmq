package com.jbyf.rabbitmq_4.direct;

import com.jbyf.rabbitmq_4.direct.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import static com.jbyf.rabbitmq_4.direct.utils.RabbitMQUtils.EXCHANGE_NAME;


public class Provider {


    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"driect");
        //发布订单操作的消息
        String msg  =" insert into t_order values( id,price)";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,msg.getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

package com.jbyf.rabbitmq_5.origin.simple;


import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Custmer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare("simple-queue",false,false,false,null);

        Consumer consume = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者获得" + new String(body));
            }
        };
        /**
         * 参数1 : 队列名称
         * 参数2 : 是否持久化
         * 参数3 : consume 对象
         */
        channel.basicConsume("simple-queue",false,consume);


    }
}

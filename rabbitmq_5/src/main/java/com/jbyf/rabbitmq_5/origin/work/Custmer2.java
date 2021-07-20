package com.jbyf.rabbitmq_5.origin.work;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Custmer2 {
    public static void main(String[] args) throws  Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("work-queue",false,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2" + new String(body));
            }
        };
        channel.basicConsume("work-queue",false,consumer);

    }
}

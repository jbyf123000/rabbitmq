package com.jbyf.rabbitmq_5.origin.topic;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        final String TYPE = "topic";
        final String QUEUE_NAME = RabbitMQUtils.BASIC_QUEUE_NAME + TYPE + 2 ;
        final String EXCHANGE_NAME = TYPE + RabbitMQUtils.BASIC_EXCHANGE_NAME;

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.woshinidie.*");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2 : " + new String(body));
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}

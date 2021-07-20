package com.jbyf.rabbitmq_4.direct;

import com.jbyf.rabbitmq_4.direct.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

import static com.jbyf.rabbitmq_4.direct.utils.RabbitMQUtils.BASIC_QUEUE_NAME;
import static com.jbyf.rabbitmq_4.direct.utils.RabbitMQUtils.EXCHANGE_NAME;

public class Consume2 {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(BASIC_QUEUE_NAME + 2,false,false,false,null);
        //队列绑定
        channel.exchangeBind(BASIC_QUEUE_NAME + 2,EXCHANGE_NAME,"insert");
        channel.exchangeBind(BASIC_QUEUE_NAME + 2,EXCHANGE_NAME,"select");
        channel.exchangeBind(BASIC_QUEUE_NAME + 2,EXCHANGE_NAME,"delete");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2" + new String(body));
            }
        };
        channel.basicConsume(BASIC_QUEUE_NAME + 2,false,consumer);
    }
}

package com.jbyf.rabbitmq_5.origin.direct;

import com.jbyf.rabbitmq_5.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        final String QUEUE_NAME = RabbitMQUtils.BASIC_QUEUE_NAME + "1";
        final String EXCHANGE_NAME = "direct" +RabbitMQUtils.BASIC_EXCHANGE_NAME;

        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //给队列绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"select");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");

        //监听
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1 " + new String(body));
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}

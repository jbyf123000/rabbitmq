package com.jbyf.rabbitmq_3.consumer;

import com.jbyf.rabbitmq_3.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    private final static String QUEUE_NAME = "fanout_exchange_queue_1";

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {

            connection = RabbitMQUtils.getConnection();
            channel = connection.createChannel();

            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 绑定队列到交换机
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body);
                    System.out.println(" [消费者1] received : " + msg + "!");
                }
            };
            channel.basicConsume(QUEUE_NAME,true,consumer);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}

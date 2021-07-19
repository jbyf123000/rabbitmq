package com.jbyf.rabbitmq_3.provider;


import com.jbyf.rabbitmq_3.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 广播模式:
 *  - 1）  可以有多个消费者
 * - 2）  每个**消费者有自己的queue**（队列）
 * - 3）  每个**队列都要绑定到Exchange**（交换机）
 * - 4）  **生产者发送的消息，只能发送到交换机**，交换机来决定要发给哪个队列，生产者无法决定。
 * - 5）  交换机把消息发送给绑定过的所有队列
 * - 6）  队列的消费者都能拿到消息。实现一条消息被多个消费者消费
 */
public class Provider {
    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) {
       try {
           Connection connection = RabbitMQUtils.getConnection();
           Channel channel = connection.createChannel();
           // exchangeDeclare(声明的交换机名,模型名称)
           channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

           //消息内容
           String message = "woshinidie";

          channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
           System.out.println(" [生产者] Sent '" + message + "'");

           RabbitMQUtils.closeConnectionAndChanel(channel,connection);

       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}

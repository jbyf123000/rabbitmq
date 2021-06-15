package com.jbyf;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class Consumer {
    public static void main(String[] args) throws Exception {
        //建立rabbitmq连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.44.129");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        //创建连接对象
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //生产者和消费者的通道配置必须相同
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数1 : 指定消费队列名称
        //参数2 : 开启消费自动确认
        //参数3 : 消费消息时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            //最后一个参数: 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
        //不建议在consumer端关闭连接和通道
//        channel.close();
//        connection.close();
    }
}

package com.jbyf;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//第一种rabbitmq的模型
public class Provider{
    //生产消息的代码
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //创建连接mq的连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置rabbitmq的主机
        connectionFactory.setHost("192.168.44.129");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //通过连接获取连接中的通道对象
        Channel channel = connection.createChannel();

        //通道绑定对应的消息队列
        //参数1 : queue       队列名称    如果队列不存在则自动创建
        //参数2 : durable     定义队列特性是否要持久化
        //参数3 : exclusive   是否独占队列--当前队列是否只为本链接使用
        //参数4 : autoDelete  是否在消费完成后自动删除队列
        //参数5 : 额外参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1 : exchange    交换机名称
        //参数2 : 队列名称
        //参数3 : 传递消息的额外设置
        //参数4 : 消息的具体内容
        channel.basicPublish("", "hello",null,"hello,rabbitmq".getBytes());

        channel.close();
        connection.close();

    }
}

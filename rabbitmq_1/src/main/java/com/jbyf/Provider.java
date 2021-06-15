package com.jbyf;

import com.jbyf.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//第一种rabbitmq的模型
public class Provider{
    //生产消息的代码
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //通过连接获取连接中的通道对象
        Channel channel = connection.createChannel();

        //通道绑定对应的消息队列
        //参数1 : queue       队列名称    如果队列不存在则自动创建
        //参数2 : durable     定义队列特性是否要持久化    如果没有设置持久化,rabbit服务重启时会销毁队列,队列中的消息也会丢失,如果要保证消息的持久化,需要在发布消息时添加额外的设置
        //参数3 : exclusive   是否独占队列--当前队列是否只为本链接使用
        //参数4 : autoDelete  是否在消费完成后自动删除队列
        //参数5 : 额外参数
        channel.queueDeclare("hello3",true,false,true,null);

        //发布消息
        //参数1 : exchange    交换机名称
        //参数2 : 队列名称
        //参数3 : 传递消息的额外设置   可以在此处设置队列消息持久化  MessageProperties.PERSISTENT_TEXT_PLAIN:持久化消息
        //参数4 : 消息的具体内容
        channel.basicPublish("", "hello3", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello,rabbitmq".getBytes());

        //关闭连接
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

public class Provider {
    //生产者
    public static void main(String[] args) {
        try{
            //获取连接
            Connection connection = RabbitMQUtils.getConnection();
            //获取通道
            Channel channel = connection.createChannel();
            //声明队列
            channel.queueDeclare("work",true,false,false,null);
            //发布消息
            for (int i = 1; i <= 20; i++) {
                channel.basicPublish("","work",null,(i+"--hello work queue").getBytes());
            }
            RabbitMQUtils.closeConnectionAndChanel(channel,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

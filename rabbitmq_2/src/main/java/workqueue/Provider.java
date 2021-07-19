package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

/**
 *      `Work queues`，也被称为（`Task queues`），任务模型。
 *      当消息处理比较耗时的时候，可能生产消息的速度会远远大于消息的消费速度
 *      长此以往，消息就会堆积越来越多，无法及时处理。
 *      此时就可以使用work 模型：
 *          让多个消费者绑定到一个队列，共同消费队列中的消息。
 *          队列中的消息一旦消费，就会消失，因此任务是不会被重复执行的。
 */

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

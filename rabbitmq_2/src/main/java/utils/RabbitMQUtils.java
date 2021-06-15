package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    static{
        //设置连接主机
        connectionFactory.setHost("192.168.44.129");
        //设置连接主机的端口
        connectionFactory.setPort(5672);
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置连接的账号密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }


    //定义提供连接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //关闭通道和连接
    public static void closeConnectionAndChanel(Channel channel, Connection connection){
        try {
            if(channel!=null ){
                channel.close();
            }
            if(connection !=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

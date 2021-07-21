package com.jbyf.rabbitmq_5.framework.deadlatter;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//死信队列配置
@Component
public class ProducerAckConfig {

    //声明业务交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("spring.test.exchange", true, false);
    }

    /**
     * 声明死信交换机
     * @return
     */
    @Bean
    public TopicExchange deadLatterExchange(){
        return new TopicExchange("dead-exchange", true, false);
    }

    /**
     * 声明处理业务的队列
     * 并把死信交换机绑定到业务队列
     * @return
     */
    @Bean
    public Queue queue(){
        Map<String, Object> arguments = new HashMap<>();
        // 绑定死信交换机
        arguments.put("x-dead-letter-exchange","dead-exchange");
        //规定 死信 routingKey
        arguments.put("x-dead-letter-routing-key","msg.dead");
        return new Queue("spring.test.queue", true,
                false, false, arguments);
    }


    /**
     * 声明死信队列
     */
    @Bean
    public Queue deadQueue(){
        return new Queue("dead-queue", true,
                false, false);
    }



    /**
     * 业务队列绑定到业务交换机
     * @return
     */
    @Bean
    public Binding binding(){
        /*
            destination: 要绑定的队列名
            destinationType : 队列的类型 (可以通过常量Binding.DestinationType.QUEUE)
            exchange: 交换机名
            routingKey : 路径参数
            arguments: 额外参数
         */
        return new Binding("spring.test.queue",
                Binding.DestinationType.QUEUE,
                "spring.test.exchange",
                "a.b",
                null);
    }


    /**
     * 把死信队列绑定到死信交换机
     * @return
     */
    @Bean
    public Binding bindingDeadLatter(){
        return new Binding(
                "dead-queue",
                Binding.DestinationType.QUEUE,
                "dead-exchange",
                "msg.dead",
                null
        );
    }




}


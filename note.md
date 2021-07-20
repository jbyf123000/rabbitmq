## Rabbitmq



### 持久化

消息持久化的配置:

​	声明交换机时:

```java
channel.exchangeDeclare(exchangeName,exchangeTypes,durable);
```





## Spring整合 rabbitmq

`rabbitTemplate`: rabbitmq对象

`convertAndSend`: 向队列发送消息





## Springboot 实现生产者发布消息

消费者的创建:

在组件类中通过 `@RabbitListener` 标注一个方法,该方法就变成了消费者的回调方法
	
可以使用`@RabbitListener(要监听的交换机,队列,绑定关系)`


​	
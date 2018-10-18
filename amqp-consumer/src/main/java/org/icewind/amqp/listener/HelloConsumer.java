package org.icewind.amqp.listener;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.icewind.amqp.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Ye Jianyu
 * @date 2018/6/5
 */
@Component
public class HelloConsumer {

//    @RabbitListener(queues = {"hello.queue"})
    @RabbitListener(queues = {"topic.queue"})
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

    @RabbitListener(queues = {"user.queue"})
    public void processUser(User user) {
        System.out.println("Receiver2  : " + user);
    }
}

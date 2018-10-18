package org.icewind.amqp.listener;

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

    @RabbitListener(queues = "hello.queue")
//    @RabbitListener(queues = "topic.queue")
    public void process(String msg) {
        System.out.println("Receiver1  : " + msg);
    }
//
//    @RabbitListener(queues = {"user.queue"})
//    public void processUser(User user) {
//        System.out.println("Receiver1  : " + user);
//    }
}

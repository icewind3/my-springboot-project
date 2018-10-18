package org.icewind.amqp.web.controller;

import org.icewind.amqp.entity.User;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/5/23
 */
@RestController
public class ProviderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send")
    public String sendMsg(@RequestParam(required = false) String msg){
//        rabbitTemplate.convertAndSend("hello.key",msg);
//        rabbitTemplate.convertAndSend("hello.exchange","hello.key",msg);
        rabbitTemplate.convertAndSend("topic.exchange","topic.key",msg);
//        rabbitTemplate.convertAndSend("hello.queue",msg);
        return msg;
    }

    @RequestMapping(value = "/sendUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public User sendUser(@RequestParam(required = false) String userName){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(String.valueOf(Math.random()*100000));
//        rabbitTemplate.convertAndSend("hello.key",msg);
//        rabbitTemplate.convertAndSend("hello.exchange","hello.key",msg);
//        rabbitTemplate.convertAndSend("hello.queue",msg);
        try {
            rabbitTemplate.convertAndSend("hello.exchange","user.key",user);

        }catch (AmqpConnectException e){
            e.printStackTrace();
        }
        return user;
    }
}

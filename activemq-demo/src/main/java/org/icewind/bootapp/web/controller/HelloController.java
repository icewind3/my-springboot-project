package org.icewind.bootapp.web.controller;

import org.icewind.bootapp.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ye Jianyu
 * @date 2018/4/23
 */
@Controller
public class HelloController {

    @Autowired
    ProducerService producerService;

    @RequestMapping(value = "/send")
    public void sendMsg(@RequestParam String msg){
        System.out.println("发送信息：" + msg);
        producerService.send(msg);
    }
}

package org.icewind.springcloudconsumer.web.controller;

import org.icewind.springcloudconsumer.dto.User;
import org.icewind.springcloudconsumer.service.HelloService;
import org.icewind.springcloudconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/7/30
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private UserService userService;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "a") String name) {
        System.out.println(helloService.hi(name));
        return userService.sayHiFromClientOne(name);
    }

    @RequestMapping("/user")
    public User getUser(@RequestParam(value = "name", defaultValue = "a") String name) {
        System.out.println(helloService.getUser(name));
        return userService.getUserFromClientOne(name);
    }
}

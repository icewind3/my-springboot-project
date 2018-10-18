package org.icewind.springcloudprovider.web.controller;

import org.icewind.springcloudprovider.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/7/27
 */
@RestController
public class DiscoveryController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "test") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @RequestMapping("/user")
    public User getUser(@RequestParam(value = "name", defaultValue = "test") String name) {
        String msg = "hi " + name + " ,i am from port:" + port;
        User user = new User();
        user.setUsername(name);
        user.setMsg(msg);
        return user;
    }


}

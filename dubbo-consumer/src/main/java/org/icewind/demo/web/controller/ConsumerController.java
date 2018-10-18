package org.icewind.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.icewind.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/5/15
 */
@RestController
public class ConsumerController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/hello")
    public String sayHello(){
        System.out.println(demoService);
        return demoService.sayHello("oauth2");
    }
}

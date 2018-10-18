package org.icewind.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.icewind.demo.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/5/15
 */
@RestController
public class DemoConsumerController {

//    @Reference(version = "${oauth2.service.version}",
//            application = "${dubbo.application.id}",
//            url = "dubbo://localhost:12345")
//    private DemoService demoService;
//
//    @RequestMapping("/sayHello")
//    public String sayHello(@RequestParam String name) {
//        return demoService.sayHello(name);
//    }

}

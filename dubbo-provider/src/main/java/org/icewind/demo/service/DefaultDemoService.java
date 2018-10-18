package org.icewind.demo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Ye Jianyu
 * @date 2018/5/15
 */
//@Service(
//        version = "${oauth2.service.version}",
//        application = "${dubbo.application.id}",
//        protocol = "${dubbo.protocol.id}",
//        registry = "${dubbo.registry.id}"
//)
public class DefaultDemoService implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }

}
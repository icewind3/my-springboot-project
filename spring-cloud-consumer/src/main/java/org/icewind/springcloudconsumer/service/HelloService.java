package org.icewind.springcloudconsumer.service;

import org.icewind.springcloudconsumer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ye Jianyu
 * @date 2018/7/30
 */
@Service
public class HelloService {

    @Autowired
    @Qualifier("springCloudRestTemplate")
    private RestTemplate restTemplate;

    public String hi(String name){
        return restTemplate.getForObject("http://SPRING-CLOUD-CLIENT/hi?name=" + name, String.class);
    }

    public User getUser(String name){
        return restTemplate.getForObject("http://SPRING-CLOUD-CLIENT/user?name=" + name, User.class);
    }
}

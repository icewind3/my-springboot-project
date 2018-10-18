package org.icewind.springcloudconsumer.service;

import org.icewind.springcloudconsumer.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ye Jianyu
 * @date 2018/7/30
 */
@FeignClient(value = "spring-cloud-client")
public interface UserService {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    User getUserFromClientOne(@RequestParam(value = "name") String name);
}

package org.icewind.rediscluster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Jianyu
 * @date 2018/7/3
 */
@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/msg/{msg}")
    public Object test(@PathVariable(value = "msg") String msg){
        redisTemplate.opsForValue().set("a", msg);
        return redisTemplate.opsForValue().get("a");
    }
}

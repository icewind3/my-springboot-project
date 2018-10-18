package com.icewind.oauth2.web.controller;

import com.icewind.oauth2.entity.User;
import com.icewind.oauth2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author icewind
 * @date 2018/7/9
 */
@Controller
public class HelloController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public User a() {
        return userService.findByUsername("a");
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    @ResponseBody
    public User b() {
        return userService.findByUsername("b");
    }

    @RequestMapping(value = "/c", method = RequestMethod.GET)
    @ResponseBody
    public User c() {
        User user = new User();
        user.setId(2L);
        user.setUsername("a");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("c");

        User user3 = new User();
        user3.setId(2L);
        user3.setUsername("d");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        list.add(user3);

        redisTemplate.opsForValue().set("ab", list, 100, TimeUnit.SECONDS);
//        BoundListOperations<String, Object> aa = redisTemplate.boundListOps("aa");
//        aa.leftPush(user);
//        aa.leftPush("11");
//        return  (User)redisTemplate.opsForValue().get("a");
        return (User) ((List) redisTemplate.opsForValue().get("ab")).get(0);
    }

    @RequestMapping(value = "/d", method = RequestMethod.GET)
    @ResponseBody
    public String d() {
        return userService.findStringByUsername("a");
    }

    @RequestMapping(value = "/count/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Long count(@PathVariable String name) {
        System.out.println(userService.countA(name,null));
        return userService.countB(name,null);
    }


}

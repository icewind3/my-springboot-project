package com.icewind.oauthclient.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

/**
 * @author Ye Jianyu
 * @date 2018/7/6
 */
@RestController
public class TestController {

    @RequestMapping("/a")
    public String test(){
        return "a";
    }

    @RequestMapping("/user")
    public Principal user(Principal principal){
        return principal;
    }
}

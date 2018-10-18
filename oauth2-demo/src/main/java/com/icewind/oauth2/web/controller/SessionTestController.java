package com.icewind.oauth2.web.controller;

import com.icewind.oauth2.entity.User;
import com.icewind.oauth2.service.UserServiceImpl;
import com.icewind.oauth2.web.ResponseResult;
import com.icewind.oauth2.web.ResultGenerator;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icewind
 * @date 2018/7/9
 */
@RestController
@RequestMapping(value = "/admin/v1")
public class SessionTestController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        map.put("port", request.getLocalPort());
        return map;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseResult getUser(@PathVariable Long id) {
        try {
            return ResultGenerator.genSuccessResult(userService.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseResult deleteUser(@PathVariable Long id) {
        userService.deleteOne(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping(value = "/user")
    public ResponseResult createUser(@RequestBody User user) {
        return ResultGenerator.genSuccessResult(userService.addUser(user));
    }

    @PutMapping(value = "/user/{id}")
    public ResponseResult updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User oldUser = userService.findOne(id);
            oldUser.setUsername(user.getUsername());
            if (user.getSex() != null){
                oldUser.setSex(user.getSex());
            }
            return ResultGenerator.genSuccessResult(userService.updateUser(oldUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @GetMapping(value = "/sessions")
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("request Url"));
        return map;
    }


}

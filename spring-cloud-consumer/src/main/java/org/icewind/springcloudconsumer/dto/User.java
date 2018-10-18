package org.icewind.springcloudconsumer.dto;

/**
 * @author Ye Jianyu
 * @date 2018/7/30
 */
public class User {

    private String username;

    private String msg;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "User{username='" + username +
                "', msg='" + msg + "'}";
    }
}

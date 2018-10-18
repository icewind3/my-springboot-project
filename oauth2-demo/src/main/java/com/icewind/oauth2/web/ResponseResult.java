package com.icewind.oauth2.web;

/**
 * @author icewind
 * @date 2018/7/10
 */
public class ResponseResult {

    private Integer code;
    private Object data;
    private String msg;

    public ResponseResult() {
    }

    public ResponseResult(ResultCode resultCode) {
        this.code = resultCode.value();
    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult setCode(ResultCode resultCode) {
        this.code = resultCode.value();
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}

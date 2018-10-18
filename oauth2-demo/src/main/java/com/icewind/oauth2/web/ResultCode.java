package com.icewind.oauth2.web;

/**
 * @author icewind
 * @date 2018/7/10
 */
public enum ResultCode {

    /**
     * 处理成功
     */
    SUCCESS(100),

    /**
     * 处理失败
     */
    FAIL(400);

    private int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}

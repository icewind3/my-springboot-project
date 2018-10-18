package org.icewind.elasticsearchdemo.util;

/**
 * @author Ye Jianyu
 * @date 2018/9/30
 */
public class ResponseResult {

    public static final Integer OK = 0;

    public static final Integer ERROR = -1;

    private Integer resultCode;
    private String message;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer resultCode, Object data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

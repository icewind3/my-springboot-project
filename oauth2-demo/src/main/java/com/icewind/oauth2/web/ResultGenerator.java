package com.icewind.oauth2.web;

/**
 * @author icewind
 * @date 2018/7/10
 */
public class ResultGenerator {
        private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

        public static ResponseResult genSuccessResult() {
            return new ResponseResult(ResultCode.SUCCESS)
                    .setMsg(DEFAULT_SUCCESS_MESSAGE);
        }

        public static ResponseResult genSuccessResult(Object data) {
            return new ResponseResult()
                    .setCode(ResultCode.SUCCESS)
                    .setMsg(DEFAULT_SUCCESS_MESSAGE)
                    .setData(data);
        }

        public static ResponseResult genFailResult(String message) {
            return new ResponseResult()
                    .setCode(ResultCode.FAIL)
                    .setMsg(message);
        }
}

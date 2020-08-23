package com.example.demo.RPCDomain.response;

import com.example.demo.common.ResultCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -2000047757147386043L;

    /**
     * Operation code
     */
    private int code;

    /**
     * Message
     */
    private String message;

    /**
     * Result data
     */
    private T data;

    public ResponseResult() {};

    public ResponseResult(final ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ResponseResult(final ResultCode resultCode, final T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public ResponseResult(final String message) {
        this.message = message;
    }

    public static <T> ResponseResult SUCCESS(final T data) {
        return new ResponseResult(ResultCode.SUCCESS);
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ResultCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(ResultCode.FAIL);
    }

    public static ResponseResult FAIL(final String message) {
        return new ResponseResult(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

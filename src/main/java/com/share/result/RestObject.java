package com.share.result;


import com.share.enums.RestCode;

/**
 * @DesDescription: 返回对象实体
 * @param: code 状态码
 * @param: msg 提示信息
 * @param: data 数据
 * */


public class RestObject<T> {
    private int code;
    private String msg;
    private T data;

    public RestObject<T> setCode(RestCode rsetCode) {
        this.code = rsetCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RestObject<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RestObject<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestObject<T> setData(T data) {
        this.data = data;
        return this;
    }
}

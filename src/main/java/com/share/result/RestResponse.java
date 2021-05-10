package com.share.result;

import com.share.enums.RestCode;

public class RestResponse {
    private final static String SUCCESS = "success";
    private final static String FAil = "fail";

    public static <T> RestObject<T> makeOKRsp() {
        return new RestObject<T>().setCode(RestCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> RestObject<T> makeOKRsp(T data) {
        return new RestObject<T>().setCode(RestCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RestObject<T> makeErrRsp(T data) {
        return new RestObject<T>().setCode(RestCode.ERROR).setMsg(FAil).setData(data);
    }

    public static <T> RestObject<T> makeUserNotLoginRsp(T data) {
        return new RestObject<T>().setCode(RestCode.NO_USERINFO).setMsg("no user login").setData(data);
    }

    public static <T> RestObject<T> UserErrRsp(T data) {
        return new RestObject<T>().setCode(RestCode.NO_USERINFO).setMsg("user error").setData(data);
    }

    public static <T> RestObject<T> ErrRsp(int code,String msg,T data) {
        return new RestObject<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> RestObject<T> makeRsp(int code, String msg) {
        return new RestObject<T>().setCode(code).setMsg(msg);
    }

    public static <T> RestObject<T> makeRsp(int code, String msg, T data) {
        return new RestObject<T>().setCode(code).setMsg(msg).setData(data);
    }
}


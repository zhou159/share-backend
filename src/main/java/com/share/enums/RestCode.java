package com.share.enums;

public enum RestCode {
    SUCCESS(200),
    FAIL(400),
    NO_USERINFO(401),
    NOT_FOUND(404),
    ERROR(500);

    public int code;

    RestCode(int code){
        this.code = code;
    }

}

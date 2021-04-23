package com.share.enums;

import lombok.Getter;
/*
* 随机数源字符
*  */

@Getter
public enum Source {
    num("1234567890"),
    numLetter("23456789ABCDEFGHJKLMNPQRSTUVWXYZ"),
    letter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    symbolNumLetter("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_*/+-!@#$%^&*()-=~");

    public String sources;

    Source(String sources){this.sources = sources;}



}

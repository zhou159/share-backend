package com.share.enums;

import lombok.Getter;
/*
* 随机数源字符
*  */

@Getter
public enum Source {
    num("1234567890"),
    numLetter("23456789ABCDEFGHJKLMNPQRSTUVWXYZ"),
    Letter("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    public String sources;

    Source(String sources){this.sources = sources;}



}

package com.share.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


public class UUidUtil {

    @Test
    public void test(){
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);


        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    }



}

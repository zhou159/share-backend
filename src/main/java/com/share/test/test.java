package com.share.test;

import com.share.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void up(){
        String a = "A1-bcde";
        System.out.println(a.toUpperCase());
        System.out.println(a.toLowerCase());
    }

    @Test
    public void redis2(){
//        redisUtil.set("zzz","1");
        Object o = 2;
        Object o1 = null;
        int value =2;
//        System.out.println(new Integer((Integer) o));
        if ( new Integer((Integer) o) == value ){
            System.out.println("false");
        }
        if (o1 == null){
            System.out.println("null");
        }
    }

    @Test
    public void sss(){
        int x =1;
        int y=2;
        System.out.println(x-y);
    }
}

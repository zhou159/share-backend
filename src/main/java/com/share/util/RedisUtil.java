package com.share.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    @Resource(name = "myRedisTemplate")
    private RedisTemplate myRedisTemplate;

    /**
     * @param key:key
     * @param value:value
     * @description 设置一个String类键
     * */
    public void set(String key,Object value){
        try {
            myRedisTemplate.opsForValue().set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key:key
     * @param value:value
     * @param time:时间，单位（分）
     * @description 设置一个带时间的String类键
     * */

    public void setex(String key,Object value,Integer time){
        try {
            myRedisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object get(String key){
        Object o = null;
        try {
            o = myRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}

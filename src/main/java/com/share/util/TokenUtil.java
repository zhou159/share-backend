package com.share.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.share.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtil {
    @Autowired
    RedisUtil redisUtil;

    //创建包含用户信息的token
    public String getToken(User user) {
        String token="";
        token= JWT.create()
                .withClaim("id",user.getId())
                .sign(Algorithm.HMAC256(String.valueOf(user.getId())));
        String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
        redisUtil.setex(uuid,token,24 * 60);
        return uuid;
    }


}

package com.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/22 19:50
 */
public class JWTUtils {
    //过期时间

    //生成Token
    public static String getToken(String userId, String userPassword) {
        return JWT.create().withAudience(userId)
                .withExpiresAt(new Date(24 * 60 * 60))
                .sign(Algorithm.HMAC256(userPassword));
    }

}

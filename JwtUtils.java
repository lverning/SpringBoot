package com.springboottest01.utlis;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/3/26 16:03
 */
public class JwtUtils {

    public static String createToken(String userId, String userName){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,30);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("userid",userId)
                .withClaim("userName", userName)    //载荷，随便写几个都可以
                .sign(Algorithm.HMAC256(userName+"abcdefg"));   //加密
    }

//    public static void main(String[] args) {
//        String token = JwtUtils.createToken("1", "2");
//        System.out.println(token);
//    }

}

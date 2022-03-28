package com.springboottest01.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springboottest01.exception.ServiceException;
import com.springboottest01.mapper.SysUserMapper;
import com.springboottest01.model.SysUser;
import com.springboottest01.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.spi.SoundbankReader;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO(验证token, 拦截器)
 * @date 2022/3/27 14:02
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * token 获取请求头中的token
         * sing 获取请求头中的token密钥
         */
        String token = request.getHeader("token");
        System.out.println("token----->" + token);
        String sign = request.getHeader("sign");
        // 如果不是映射到方法直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 执行认证
        if (token == null || token.length() == 0) {
            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
        }
        // 获取token中的userId
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        // 根据token中的userId查询数据库
        SysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
        }
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUsername() + sign)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        return true;
    }
}

package com.shiro.config;

import com.shiro.pojo.User;
import com.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author： 吕二宁
 * @date： 2021-12-28 16:49
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了===>授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了===>认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User userById = userService.getUserById(userToken.getUsername());
        System.err.println(userById);
        if (userById == null){
            return null;
        }

        return new SimpleAuthenticationInfo("",userById.getUserPassword(),"");
    }
}

## Shiro

##### Shiro三大对象：

- Subject用户
- SecurityManage 管理所有用户
- Realm 连接数据

​	导入Spring Boot---Shiro整合包

​			

```xml
   <dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>
    </dependency>
```

入门：创建自定义的UserRelalm

```java
//自定义的UserRealm 继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
```

创建Shiro配置类：

```java
@Configuration
public class ShiroConfig {
    //创建UserRealm对象，需要自定义类1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //DefaultWebSecurityManager2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //ShiroFilterFactoryBean3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
    }
}

```


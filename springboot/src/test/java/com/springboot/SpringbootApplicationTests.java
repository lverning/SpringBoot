package com.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.mapper.RoleMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.Role;
import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@MapperScan("com.springboot.mapper")
@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        Page<User> page = new Page<>(1, 5);
//        userMapper.selectPage(page,null);
//        page.getRecords().forEach(System.out::println);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName","1");
        queryWrapper.eq("userPassword","1");
        User user = userMapper.Login("孙磊", "0000000");
        System.out.println(user.toString());

    }

    @Test
    public void test(){
        List<Role> roleList = roleMapper.getRoleList();
        for (Role role : roleList) {
            System.out.println(role);
        }
    }

    @Test
    public void test2(){
        User userinfo = userService.getById(10);
        System.out.println(userinfo.toString());
    }

    @Test
    public void test3(){
        User user = new User();
        user.setId(1);
        user.setUserPassword("11");
        boolean user1 = userService.updateUser(user);
        System.out.println(user1);
    }
}

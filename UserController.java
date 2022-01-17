package com.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/13 23:12
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList/{pageNum}/{pageSize}")
    public List<User> getUserList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        List<User> userList = userService.getUserList(pageNum, pageSize);
        return userList;
    }

    @GetMapping("/count")
    public int count(){
        int count = userService.count();
        return count;
    }


    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") Integer id) {
        int i = userService.deleteUserById(id);
        return i;
    }

    @PostMapping("/addUser")
    public String addUser(String userName,
                          String address,
                          String phone,
                          String creationDate) throws ParseException {
        User user = new User();
        user.setUserName(userName);
        user.setAddress(address);
        user.setPhone(phone);
        Map<String, String> map = new HashMap<>();
        int i = userService.addUser(user);
        if (i > 0) {
            return "success";
        } else {
            return "false";
        }
    }
}

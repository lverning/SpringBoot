package com.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.common.Constants;
import com.springboot.controller.dto.UserDTO;
import com.springboot.exception.ServiceException;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import com.springboot.service.UserService;
import com.springboot.utils.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/15 19:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public UserDTO Login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userDTO.getUserName());
        queryWrapper.eq("userPassword", userDTO.getUserPassword());
        try {
            User one = getOne(queryWrapper);
            if (one != null) {
                System.out.println("------------"+ one);
                BeanUtils.copyProperties(one, userDTO);
                String token = JWTUtils.getToken(one.getId().toString(), one.getUserPassword());
                userDTO.setToken(token);
                return userDTO;
            } else {
                throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
        }
    }



    @Override
    public List<User> getUserList(Integer pageNum, Integer pageSize, String seleceText) {
        return userMapper.getUserList(pageNum, pageSize, seleceText);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public boolean saveUser(User user) {
        System.out.println("save" + user);
        return userMapper.saveUser(user);
    }

    @Override
    public User userinfo(Integer id) {
        User user = userMapper.userinfo(id);
        if (user != null){
            return user;
        }else {
            throw new ServiceException(Constants.CODE_401,"用户不存在");
        }
    }

    @Override
    public boolean updateUser( User user) {
        int i = userMapper.updateById(user);
        try {
            if (i != 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_401,"参数错误");
        }
    }


}

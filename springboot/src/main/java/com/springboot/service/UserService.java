package com.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.controller.dto.UserDTO;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/15 19:28
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     *
     * @return Result通用返回类
     */
    UserDTO Login(UserDTO userDTO);

    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    List<User> getUserList(@Param("pageNum") Integer pageNum,
                       @Param("pageSize") Integer pageSize,
                           @Param("seleceText")String seleceText);


    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return
     */
    int deleteUserById(@Param("id") Integer id);

    /**
     * 添加用户
     *
     * @return
     */
    boolean saveUser(User user);

    User userinfo(Integer id);

    boolean updateUser(User user);
}

package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/15 19:28
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用户登录
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @return Result通用返回类
     */
    User Login(@Param("userName") String userName,
               @Param("userPassword") String userPassword
    );

    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    List<User> getUserList(@Param("pageNum") Integer pageNum,
                           @Param("pageSize") Integer pageSize,
                           @Param("seleceText") String seleceText);

    /**
     * 查询条数
     *
     * @return
     */
    int count();

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

}

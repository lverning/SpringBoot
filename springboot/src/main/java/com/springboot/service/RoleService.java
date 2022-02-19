package com.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.pojo.Role;
import com.springboot.pojo.User;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/28 21:37
 */
public interface RoleService extends IService<Role> {
    /**
     * 获取所有职位列表
     *
     * @return
     */
    List<Role> getRoleList();
}

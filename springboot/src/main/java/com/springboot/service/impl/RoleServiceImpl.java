package com.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mapper.RoleMapper;
import com.springboot.pojo.Role;
import com.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/28 21:37
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        List<Role> roleList = roleMapper.selectList(queryWrapper);
        return roleList;
    }
}

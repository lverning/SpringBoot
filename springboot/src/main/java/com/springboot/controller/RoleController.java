package com.springboot.controller;

import com.springboot.common.Constants;
import com.springboot.exception.ServiceException;
import com.springboot.pojo.Role;
import com.springboot.common.Result;
import com.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/28 21:43
 */
@RestController
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoleList")
    public Result getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        if (roleList == null) {
            throw new ServiceException(Constants.CODE_400,"获取角色列表失败");
        }
        return Result.success(roleList);
    }
}

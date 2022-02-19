package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Constants;
import com.springboot.common.Result;
import com.springboot.controller.dto.UserDTO;
import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO(用户控制器)
 * @date 2022/1/15 19:49
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * 用户登录
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/login")
    public Result Login(@RequestBody UserDTO userDTO) {
        String userName = userDTO.getUserName();
        String password = userDTO.getUserPassword();
        if (userName == null || password == null) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.Login(userDTO);
        return Result.success(dto);
    }

    /**
     * 查询用户,分页查询
     *
     * @param pageSum    起始位置
     * @param pageSize   每页数量
     * @param seleceText 模糊查询(用户姓名)
     * @return
     */
//    @GetMapping("/getUserList")
//    public Result getUserList(Integer pageSum,
//                              Integer pageSize,
//                              String seleceText) {
//        System.err.println("seleceText==>" + seleceText);
//        System.err.println(pageSize + "------" + pageSum);
//        List<User> userList = userService.getUserList(pageSum, pageSize, seleceText);
//        return Result.success(userList);
//    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping("deleteUserById/{id}")
    public Result deleteUserById(@PathVariable Integer id) {
        int i = userService.deleteUserById(id);
        Result result = new Result();
        if (i != 0) {
            result.setCode(Constants.CODE_200);
            result.setMessage("删除成功");
        } else {
            result.setCode(Constants.CODE_500);
            result.setMessage("删除失败");
        }
        return result;
    }


    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        boolean save = userService.save(user);
        if (save) {
            return Result.success("添加成功");
        } else {
            return Result.error(Constants.CODE_500, "添加失败");
        }
    }


    @GetMapping("/getUserList")
    public Result pages(@RequestParam Integer pageNum,
                        @RequestParam Integer pageSize,
                        @RequestParam(defaultValue = "") String userName,
                        @RequestParam(defaultValue = "") String userPassword) {
        IPage<User> Page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(userName)) {
            queryWrapper.like("userName", userName);
        }
        if (!"".equals(userPassword)) {
            queryWrapper.like("userPassword", userPassword);
        }
        IPage<User> page = userService.page(Page, queryWrapper);
        return Result.success(page);
    }

    // 查询个人信息
    @GetMapping("/userInFo/{id}")
    public Result userInFo(@PathVariable Integer id) {
        User userinfo = userService.userinfo(id);
        if (userinfo != null) {
            return Result.success(userinfo);
        } else {
            return Result.error(Constants.CODE_401, "查询出错");
        }
    }

    //修改用户信息

    @PostMapping("/updateUserById")
    public Result updateUserById(@RequestBody User user) {
        boolean updateUser = userService.updateUser(user);
        if (updateUser) {
            return Result.success("修改成功");
        } else {
            return Result.error(Constants.CODE_401, "修改失败");
        }
    }

    //根据id查询用户
    @GetMapping("/selectUserById")
    public Result selectUserById(Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }
}

package com.springboottest01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottest01.exception.ServiceException;
import com.springboottest01.mapper.SysUserMapper;
import com.springboottest01.model.SysUser;
import com.springboottest01.utlis.JwtUtils;
import com.springboottest01.vo.Constants;
import com.springboottest01.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 8:27
 */
@Service
public class SysUserMapperService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询所有用户
     *
     * @return
     */
    public ResultResponse allSysUser() {
        List<SysUser> sysusers = sysUserMapper.selectList(null);
        if (sysusers != null) {
            return ResultResponse.success(Constants.CODE_200, sysusers);
        } else {
            return ResultResponse.error(Constants.CODE_401, "查询出错");
        }
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    public ResultResponse deleteSysUser(Integer id) {
        if (id == 0) {
            return ResultResponse.error(Constants.CODE_401, "参数出错");
        }
        int deleteById = sysUserMapper.deleteById(id);
        if (deleteById != 0) {
            return ResultResponse.success(Constants.CODE_200, null);
        } else {
            return ResultResponse.error(Constants.CODE_401, "删除失败");
        }
    }

    /**
     * 用户登录
     *
     * @param phone 用户名
     * @param password 用户密码
     * @return
     */
    public ResultResponse login(String phone, String password) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", password);
        try {
            SysUser selectOne = sysUserMapper.selectOne(queryWrapper);
            if (selectOne != null) {
                String token = JwtUtils.createToken(String.valueOf(selectOne.getId()), selectOne.getPhone());
                return ResultResponse.success(Constants.CODE_200, token);
            } else {
                throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public ResultResponse insertUser(SysUser user) {
        if (user.getUsername() == null || "".equals(user.getUsername())
                || user.getPassword() == null || "".equals(user.getPassword())
                || user.getPhone() == null || "".equals(user.getPhone())) {
            throw new ServiceException(Constants.CODE_401, "信息不能为空");
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", user.getPhone());
//        SysUser selectOne = sysUserMapper.selectOne(queryWrapper);
        if (sysUserMapper.selectOne(queryWrapper) == null) {
            int insert = sysUserMapper.insert(user);
            if (insert != 0) {
                String token = JwtUtils.createToken(String.valueOf(user.getId()), user.getUsername());
                return ResultResponse.success(Constants.CODE_200, token);
            } else {
                throw new ServiceException(Constants.CODE_401, "注册失败");
            }
        } else {
            throw new ServiceException(Constants.CODE_401, "手机号重复");
        }
    }
}

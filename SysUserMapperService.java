package com.springboottest01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottest01.exception.ServiceException;
import com.springboottest01.mapper.SysUserMapper;
import com.springboottest01.model.SysUser;
import com.springboottest01.utlis.JwtUtils;
import com.springboottest01.vo.Constants;
import com.springboottest01.vo.ResultReponse;
import org.springframework.beans.BeanUtils;
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

    public ResultReponse allSysUser() {
        List<SysUser> sysusers = sysUserMapper.selectList(null);
        if (sysusers != null) {
            return ResultReponse.success(Constants.CODE_200, sysusers);
        } else {
            return ResultReponse.error(Constants.CODE_401, "查询出错");
        }
    }

    public ResultReponse deleteSysUser(Integer id) {
        if (id == 0) {
            return ResultReponse.error(Constants.CODE_401, "参数出错");
        }
        int deleteById = sysUserMapper.deleteById(id);
        if (deleteById != 0) {
            return ResultReponse.success(Constants.CODE_200, null);
        } else {
            return ResultReponse.error(Constants.CODE_401, "删除失败");
        }
    }

    public ResultReponse login(String username, String password) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        try {
            SysUser selectOne = sysUserMapper.selectOne(queryWrapper);
            if (selectOne != null) {
                String token = JwtUtils.createToken(String.valueOf(selectOne.getId()), selectOne.getUsername());
                return ResultReponse.success(Constants.CODE_200,token);
            }else {
                throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
        }


    }
}

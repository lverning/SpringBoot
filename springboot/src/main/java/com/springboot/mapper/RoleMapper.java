package com.springboot.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/28 21:34
 */
@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取所有职位列表
     *
     * @return
     */
    List<Role> getRoleList();
}

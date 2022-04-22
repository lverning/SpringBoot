package com.springboottest01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboottest01.mapper.ImgsMapper;
import com.springboottest01.model.Imgs;
import com.springboottest01.service.ImgsService;
import com.springboottest01.vo.Constants;
import com.springboottest01.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/4/6 9:19
 */
@Service
public class ImgsServiceImpl extends ServiceImpl<ImgsMapper, Imgs> implements ImgsService {


    @Autowired
    private ImgsMapper imgsMapper;

    public ResultResponse allImgs() {
        List<Imgs> imgs = imgsMapper.selectList(null);
        if (imgs != null) {
            return ResultResponse.success(Constants.CODE_200, imgs);
        } else {
            return ResultResponse.error(Constants.CODE_401, "查询出错");
        }
    }
}

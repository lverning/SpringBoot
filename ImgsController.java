package com.springboottest01.controller;

import com.springboottest01.service.impl.ImgsServiceImpl;
import com.springboottest01.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/4/6 9:17
 */
@RestController
@RequestMapping("/imgs")
public class ImgsController {

    @Autowired
    private ImgsServiceImpl imgsService;

    @GetMapping("/all")
    public ResultResponse allImgs(){
        return imgsService.allImgs();
    }
}

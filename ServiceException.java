package com.springboot.exception;

import lombok.Getter;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO(异常类)
 * @date 2022/2/12 22:10
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}

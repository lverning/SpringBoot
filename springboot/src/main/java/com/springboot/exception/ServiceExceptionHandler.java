package com.springboot.exception;

import com.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/2/12 22:09
 */
@ControllerAdvice
public class ServiceExceptionHandler {
    /**
     * @param se 业务异常
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
     }
}

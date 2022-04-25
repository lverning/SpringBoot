package com.example.l20220425.exception;

import com.example.l20220425.response.Result;
import com.example.l20220425.response.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/4/25 16:30
 */
@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result Execption(Exception e) {
        log.error("未知异常！", e);
        return Result.error(ResultMsgEnum.SERVER_BUSY.getCode(), ResultMsgEnum.SERVER_BUSY.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result Execption(ArithmeticException e){
        log.error("自定义算数异常！", e);
        return Result.error(ResultMsgEnum.COUNT.getCode(), ResultMsgEnum.COUNT.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result Execption(NullPointerException e){
        log.error("空指针异常！", e);
        return Result.error(ResultMsgEnum.COUNT.getCode(), ResultMsgEnum.COUNT.getMessage());
    }
}

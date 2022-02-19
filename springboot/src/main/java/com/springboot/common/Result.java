package com.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO(接口统一返回包装类)
 * @date 2022/1/15 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String message;
    private Object date;

    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    public static Result success(Object date) {
        return new Result(Constants.CODE_200, "", date);
    }

    public static Result error(String code, String message){
        return new Result(code,message,null);
    }

    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null);
    }

}

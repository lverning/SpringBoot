package com.springboot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/2/12 22:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String userName;
    private String userPassword;
    private String token;
}

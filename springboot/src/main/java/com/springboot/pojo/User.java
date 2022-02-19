package com.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/13 23:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "smbms_user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;           // id
    @TableField("userCode")
    private String userCode;     // 用户编码
    @TableField("userName")
    private String userName;     // 用户名称
    @TableField("userPassword")
    private String userPassword; // 用户密码
    private Integer gender;       // 性别
    private Date birthday;     // 出生日期
    private String phone;        // 电话
    private String address;      // 地址
    @TableField("userRole")
    private Integer userRole;     // 用户角色ID
    @TableField("createdBy")
    private Integer createdBy;    // 创建者
    @TableField("creationDate")
    private Date creationDate; // 创建时间
    @TableField("modifyBy")
    private Integer modifyBy;     // 更新者
    @TableField("modifyDate")
    private Date modifyDate;   // 更新时间
    @TableField(exist = false)
    private String userRoleName; // 用户角色名称
}

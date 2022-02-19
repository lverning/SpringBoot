package com.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/1/28 21:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "smbms_role")
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;   //id
    @TableField("roleCode")
    private String roleCode; //角色编码
    @TableField("roleName")
    private String roleName; //角色名称
    @TableField("createdBy")
    private Integer createdBy; //创建者
    @TableField("creationDate")
    private Date creationDate; //创建时间
    @TableField("modifyBy")
    private Integer modifyBy; //更新者
    @TableField("modifyDate")
    private Date modifyDate;//更新时间
}

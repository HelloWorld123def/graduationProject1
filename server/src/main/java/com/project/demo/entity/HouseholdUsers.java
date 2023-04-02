package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 住户用户：(HouseholdUsers)表实体类
 *
 */
@TableName("`household_users`")
@Data
@EqualsAndHashCode(callSuper = false)
public class HouseholdUsers implements Serializable {

    // HouseholdUsers编号
    @TableId(value = "household_users_id", type = IdType.AUTO)
    private Integer household_users_id;

    // 住户姓名
    @TableField(value = "`resident_name`")
    private String resident_name;
    // 住户性别
    @TableField(value = "`household_gender`")
    private String household_gender;






    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;



    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

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
 * 商户用户：(MerchantUser)表实体类
 *
 */
@TableName("`merchant_user`")
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantUser implements Serializable {

    // MerchantUser编号
    @TableId(value = "merchant_user_id", type = IdType.AUTO)
    private Integer merchant_user_id;

    // 商户姓名
    @TableField(value = "`merchant_name`")
    private String merchant_name;
    // 商户性别
    @TableField(value = "`merchant_gender`")
    private String merchant_gender;
    // 店铺名称
    @TableField(value = "`store_name`")
    private String store_name;






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

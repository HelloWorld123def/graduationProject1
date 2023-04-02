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
 * 商品信息：(ProductInformation)表实体类
 *
 */
@TableName("`product_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInformation implements Serializable {

    // ProductInformation编号
    @TableId(value = "product_information_id", type = IdType.AUTO)
    private Integer product_information_id;

    // 店铺名称
    @TableField(value = "`store_name`")
    private String store_name;
    // 商户信息
    @TableField(value = "`merchant_information`")
    private Integer merchant_information;
    // 商品名称
    @TableField(value = "`trade_name`")
    private String trade_name;
    // 商品价格
    @TableField(value = "`commodity_price`")
    private Integer commodity_price;
    // 商品图片
    @TableField(value = "`product_picture`")
    private String product_picture;
    // 商品介绍
    @TableField(value = "`product_introduction`")
    private String product_introduction;

    // 点击数
    @TableField(value = "hits")
    private Integer hits;

    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;







    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

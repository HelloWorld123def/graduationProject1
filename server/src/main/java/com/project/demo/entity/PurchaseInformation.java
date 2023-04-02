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
 * 购买信息：(PurchaseInformation)表实体类
 *
 */
@TableName("`purchase_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInformation implements Serializable {

    // PurchaseInformation编号
    @TableId(value = "purchase_information_id", type = IdType.AUTO)
    private Integer purchase_information_id;

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
    private String commodity_price;
    // 住户信息
    @TableField(value = "`resident_information`")
    private Integer resident_information;
    // 住户姓名
    @TableField(value = "`resident_name`")
    private String resident_name;
    // 联系电话
    @TableField(value = "`contact_number`")
    private String contact_number;
    // 购买数量
    @TableField(value = "`purchase_quantity`")
    private Integer purchase_quantity;
    // 购买总价
    @TableField(value = "`total_purchase_price`")
    private String total_purchase_price;
    // 配送地址
    @TableField(value = "`shipping_address`")
    private String shipping_address;
    // 购买备注
    @TableField(value = "`purchase_remarks`")
    private String purchase_remarks;
    // 订单状态
    @TableField(value = "`order_status`")
    private String order_status;
    // 配送信息
    @TableField(value = "`delivery_information`")
    private String delivery_information;




    // 支付状态
    @TableField(value = "pay_state")
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @TableField(value = "pay_type")
    private String pay_type;





    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

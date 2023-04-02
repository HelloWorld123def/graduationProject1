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
 * 类型管理：(TypeManagement)表实体类
 *
 */
@TableName("`type_management`")
@Data
@EqualsAndHashCode(callSuper = false)
public class TypeManagement implements Serializable {

    // TypeManagement编号
    @TableId(value = "type_management_id", type = IdType.AUTO)
    private Integer type_management_id;

    // 服务类型
    @TableField(value = "`service_type`")
    private String service_type;
    // 类型描述
    @TableField(value = "`type_description`")
    private String type_description;









    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

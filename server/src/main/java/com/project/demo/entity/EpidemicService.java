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
 * 疫情服务：(EpidemicService)表实体类
 *
 */
@TableName("`epidemic_service`")
@Data
@EqualsAndHashCode(callSuper = false)
public class EpidemicService implements Serializable {

    // EpidemicService编号
    @TableId(value = "epidemic_service_id", type = IdType.AUTO)
    private Integer epidemic_service_id;

    // 服务名称
    @TableField(value = "`service_name`")
    private String service_name;
    // 服务类型
    @TableField(value = "`service_type`")
    private String service_type;
    // 发布日期
    @TableField(value = "`release_date`")
    private Timestamp release_date;
    // 封面图片
    @TableField(value = "`cover_photo`")
    private String cover_photo;
    // 服务介绍
    @TableField(value = "`service_introduction`")
    private String service_introduction;

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

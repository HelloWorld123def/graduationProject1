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
 * 申请信息：(ApplicationInformation)表实体类
 *
 */
@TableName("`application_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationInformation implements Serializable {

    // ApplicationInformation编号
    @TableId(value = "application_information_id", type = IdType.AUTO)
    private Integer application_information_id;

    // 服务名称
    @TableField(value = "`service_name`")
    private String service_name;
    // 服务类型
    @TableField(value = "`service_type`")
    private String service_type;
    // 住户信息
    @TableField(value = "`resident_information`")
    private Integer resident_information;
    // 住户姓名
    @TableField(value = "`resident_name`")
    private String resident_name;
    // 联系电话
    @TableField(value = "`contact_number`")
    private String contact_number;
    // 申请备注
    @TableField(value = "`application_remarks`")
    private String application_remarks;



    // 审核状态
    @TableField(value = "examine_state")
    private String examine_state;


    // 审核回复
    @TableField(value = "examine_reply")
    private String examine_reply;




    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 读者信息
 * @TableName reader
 */
@ApiModel(value = "Reader对象", description = "读者")
@TableName(value ="reader")
@Data
public class Reader implements Serializable {
    /**
     * 读者id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("读者id")
    private Integer readerId;

    /**
     * 读者姓名
     */
    @ApiModelProperty("读者姓名")
    private String readerName;

    /**
     * 读者性别：0为女，1为男，2为保密，默认为2
     */
    @ApiModelProperty("读者性别：0为女，1为男，2为保密，默认为2")
    private Integer readerSex;

    /**
     * 读者年龄：默认为18
     */
    @ApiModelProperty("读者年龄：默认为18")
    private Integer readerAge;

    /**
     * 读者存款：默认为0
     */
    @ApiModelProperty("读者存款：默认为0")
    private Integer saving;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除标志：0为未删除、1为已删除，默认为0
     */
    @ApiModelProperty("是否删除标志：0为未删除、1为已删除，默认为0")
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

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
 * 出版社信息
 * @TableName publishing_company
 */
@ApiModel(value = "PublishingCompany对象", description = "出版社")
@TableName(value ="publishing_company")
@Data
public class PublishingCompany implements Serializable {
    /**
     * 出版社id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("出版社id")
    private Integer publishingCompanyId;

    /**
     * 出版社名称
     */
    @ApiModelProperty("出版社名称")
    private String publishingCompanyName;

    /**
     * 出版社联系方式
     */
    @ApiModelProperty("出版社联系方式")
    private String publishingCompanyTelephoneNumber;

    /**
     * 出版社通信地址
     */
    @ApiModelProperty("出版社通信地址")
    private String publishingCompanyAddress;

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

    /**
     * 出版社名下作品数量
     */
    @TableField(exist = false)
    @ApiModelProperty("出版社名下作品数量")
    private long bookNumber;
}

package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 出版社信息
 * @TableName publishing_company
 */
@TableName(value ="publishing_company")
@Data
public class PublishingCompany implements Serializable {
    /**
     * 出版社id
     */
    @TableId(type = IdType.AUTO)
    private Integer publishingCompanyId;

    /**
     * 出版社名称
     */
    private String publishingCompanyName;

    /**
     * 出版社电话号
     */
    private String publishingCompanyTelephoneNumber;

    /**
     * 出版社地址
     */
    private String publishingCompanyAddress;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除标志，0为未删除、1为已删除，默认为0
     */
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 书籍信息
 * @TableName book
 */
@ApiModel(value = "Book对象", description = "书籍")
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 书籍id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("书籍id")
    private Integer bookId;

    /**
     * 书籍书名
     */
    @ApiModelProperty("书籍书名")
    private String bookName;

    /**
     * 书籍作者id
     */
    @ApiModelProperty("书籍作者id")
    private Integer authorId;

    /**
     * 书籍IBSN号：默认为[IBSN_is_not_exist]
     */
    @ApiModelProperty("书籍IBSN号：默认为[IBSN_is_not_exist]")
    private String ibsn;

    /**
     * 书籍出版社id：id为12时代表无出版社，默认为12
     */
    @ApiModelProperty("书籍出版社id：id为12时代表无出版社，默认为12")
    private Integer publishingCompanyId;

    /**
     * 书籍类型标签id
     */
    @ApiModelProperty("书籍类型标签id")
    private Integer tagId;

    /**
     * 书籍库存数量：默认为1
     */
    @ApiModelProperty("书籍库存数量：默认为1")
    private Integer quantity;

    /**
     * 书籍单价：单价为0时代表该书为公益书籍，默认为0
     */
    @ApiModelProperty("书籍单价：单价为0时代表该书为公益书籍，默认为0")
    private Integer price;

    /**
     * 书籍是否外借标志：0为否、>=1为是，默认为0，当值大于库存数量时无法借出
     */
    @ApiModelProperty("书籍是否外借标志：0为否、>=1为是，默认为0，当值大于库存数量时无法借出")
    private Integer isBeingBorrowed;

    /**
     * 书籍出版日期：默认为'01-1月-1900'
     */
    @ApiModelProperty("书籍出版日期：默认为'01-1月-1900'")
    private LocalDate publicationDate;

    /**
     * 书籍是否为共同创作标志：0为否、>=1则为共同作者表id号，默认为0
     */
    @ApiModelProperty("书籍是否为共同创作标志：0为否、>=1则为共同作者表id号，默认为0")
    private Integer jointAuthorTableId;

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
}

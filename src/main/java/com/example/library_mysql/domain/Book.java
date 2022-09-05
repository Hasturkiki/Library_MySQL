package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
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
     * 该书id
     */
    @TableId(type = IdType.AUTO)
    private Integer bookId;

    /**
     * 该书书名
     */
    private String bookName;

    /**
     * 该书作者id
     */
    private Integer authorId;

    /**
     * 该书IBSN号，默认为[IBSN_is_not_exist]
     */
    private String ibsn;

    /**
     * 出版社id为12时代表无出版社，默认为12
     */
    private Integer publishingCompanyId;

    /**
     * 该书类型标签id
     */
    private Integer tagId;

    /**
     * 该书库存数量，默认为1
     */
    private Integer quantity;

    /**
     * 单价为0时代表该书为公益书籍，默认为0
     */
    private Integer price;

    /**
     * 书籍是否外借，0为否、>=1为是，默认为0，当值大于库存数量时无法借出
     */
    private Integer isBeingBorrowed;

    /**
     * 书籍出版日期，默认为'01-1月-1900'
     */
    private LocalDate publicationDate;

    /**
     * 该书是否存在共同作者，0为否、>=1则为共同作者表id号，默认为0
     */
    private Integer jointAuthorTableId;

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

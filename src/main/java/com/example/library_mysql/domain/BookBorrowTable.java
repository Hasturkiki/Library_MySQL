package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 借书表信息
 * @TableName book_borrow_table
 */
@TableName(value ="book_borrow_table")
@Data
public class BookBorrowTable implements Serializable {
    /**
     * 借书表id
     */
    @TableId(type = IdType.AUTO)
    private Integer booksBorrowTableId;

    /**
     * 借书者id
     */
    private Integer readerId;

    /**
     * 所借书id
     */
    private Integer bookId;

    /**
     * 书籍是否仍处于借用中，0为否、1为是，默认为1
     */
    private Integer isBorrowing;

    /**
     * 书籍外借时间，默认为1900-01-01 08:00:00
     */
    private LocalDateTime borrowTime;

    /**
     * 书籍归还时间，默认为2022-05-11 04:00:00
     */
    private LocalDateTime backTime;

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
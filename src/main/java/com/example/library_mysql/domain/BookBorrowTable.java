package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
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
    private Date borrowTime;

    /**
     * 书籍归还时间，默认为2022-05-11 04:00:00
     */
    private Date backTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookBorrowTable other = (BookBorrowTable) that;
        return (this.getBooksBorrowTableId() == null ? other.getBooksBorrowTableId() == null : this.getBooksBorrowTableId().equals(other.getBooksBorrowTableId()))
            && (this.getReaderId() == null ? other.getReaderId() == null : this.getReaderId().equals(other.getReaderId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getIsBorrowing() == null ? other.getIsBorrowing() == null : this.getIsBorrowing().equals(other.getIsBorrowing()))
            && (this.getBorrowTime() == null ? other.getBorrowTime() == null : this.getBorrowTime().equals(other.getBorrowTime()))
            && (this.getBackTime() == null ? other.getBackTime() == null : this.getBackTime().equals(other.getBackTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBooksBorrowTableId() == null) ? 0 : getBooksBorrowTableId().hashCode());
        result = prime * result + ((getReaderId() == null) ? 0 : getReaderId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getIsBorrowing() == null) ? 0 : getIsBorrowing().hashCode());
        result = prime * result + ((getBorrowTime() == null) ? 0 : getBorrowTime().hashCode());
        result = prime * result + ((getBackTime() == null) ? 0 : getBackTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", booksBorrowTableId=" + booksBorrowTableId +
                ", readerId=" + readerId +
                ", bookId=" + bookId +
                ", isBorrowing=" + isBorrowing +
                ", borrowTime=" + borrowTime +
                ", backTime=" + backTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
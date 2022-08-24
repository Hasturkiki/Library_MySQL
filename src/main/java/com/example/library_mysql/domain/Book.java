package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 书籍信息
 * @TableName book
 */
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
    private Date publicationDate;

    /**
     * 该书是否存在共同作者，0为否、>=1则为共同作者表id号，默认为0
     */
    private Integer jointAuthorTableId;

    public String getPublicationDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(publicationDate);
    }

    public String getJointAuthorTableId() {
        return switch (jointAuthorTableId) {
            case 0 -> "无";
            default -> "有";
        };
    }

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
        Book other = (Book) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getIbsn() == null ? other.getIbsn() == null : this.getIbsn().equals(other.getIbsn()))
            && (this.getPublishingCompanyId() == null ? other.getPublishingCompanyId() == null : this.getPublishingCompanyId().equals(other.getPublishingCompanyId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getIsBeingBorrowed() == null ? other.getIsBeingBorrowed() == null : this.getIsBeingBorrowed().equals(other.getIsBeingBorrowed()))
            && (this.getPublicationDate() == null ? other.getPublicationDate() == null : this.getPublicationDate().equals(other.getPublicationDate()))
            && (this.getJointAuthorTableId() == null ? other.getJointAuthorTableId() == null : this.getJointAuthorTableId().equals(other.getJointAuthorTableId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getIbsn() == null) ? 0 : getIbsn().hashCode());
        result = prime * result + ((getPublishingCompanyId() == null) ? 0 : getPublishingCompanyId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getIsBeingBorrowed() == null) ? 0 : getIsBeingBorrowed().hashCode());
        result = prime * result + ((getPublicationDate() == null) ? 0 : getPublicationDate().hashCode());
        result = prime * result + ((getJointAuthorTableId() == null) ? 0 : getJointAuthorTableId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", bookId=" + bookId +
                ", bookName=" + bookName +
                "， authorId=" + authorId +
                ", ibsn=" + ibsn +
                ", publishingCompanyId=" + publishingCompanyId +
                ", tagId=" + tagId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", isBeingBorrowed=" + isBeingBorrowed +
                ", publicationDate=" + publicationDate +
                ", jointAuthorTableId=" + jointAuthorTableId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
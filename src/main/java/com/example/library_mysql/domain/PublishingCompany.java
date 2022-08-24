package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
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
        PublishingCompany other = (PublishingCompany) that;
        return (this.getPublishingCompanyId() == null ? other.getPublishingCompanyId() == null : this.getPublishingCompanyId().equals(other.getPublishingCompanyId()))
            && (this.getPublishingCompanyName() == null ? other.getPublishingCompanyName() == null : this.getPublishingCompanyName().equals(other.getPublishingCompanyName()))
            && (this.getPublishingCompanyTelephoneNumber() == null ? other.getPublishingCompanyTelephoneNumber() == null : this.getPublishingCompanyTelephoneNumber().equals(other.getPublishingCompanyTelephoneNumber()))
            && (this.getPublishingCompanyAddress() == null ? other.getPublishingCompanyAddress() == null : this.getPublishingCompanyAddress().equals(other.getPublishingCompanyAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPublishingCompanyId() == null) ? 0 : getPublishingCompanyId().hashCode());
        result = prime * result + ((getPublishingCompanyName() == null) ? 0 : getPublishingCompanyName().hashCode());
        result = prime * result + ((getPublishingCompanyTelephoneNumber() == null) ? 0 : getPublishingCompanyTelephoneNumber().hashCode());
        result = prime * result + ((getPublishingCompanyAddress() == null) ? 0 : getPublishingCompanyAddress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", publishingCompanyId=" + publishingCompanyId +
                ", publishingCompanyName=" + publishingCompanyName +
                "， publishingCompanyTelephoneNumber=" + publishingCompanyTelephoneNumber +
                ", publishingCompanyAddress=" + publishingCompanyAddress +
                "， serialVersionUID=" + serialVersionUID +
                "]";
    }
}
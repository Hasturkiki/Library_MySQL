package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 读者信息
 * @TableName reader
 */
@TableName(value ="reader")
@Data
public class Reader implements Serializable {
    /**
     * 读者id
     */
    @TableId(type = IdType.AUTO)
    private Integer readerId;

    /**
     * 读者姓名
     */
    private String readerName;

    /**
     * 读者性别，0为女，1为男，2为保密，默认为2
     */
    private Integer readerSex;

    /**
     * 读者年龄，默认为18
     */
    private Integer readerAge;

    /**
     * 读者存款，默认为0
     */
    private Integer saving;

    public String getReaderSex() {
        return switch (readerSex) {
            case 0 -> "女";
            case 1 -> "男";
            case 2 -> "保密";
            default -> "error";
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
        Reader other = (Reader) that;
        return (this.getReaderId() == null ? other.getReaderId() == null : this.getReaderId().equals(other.getReaderId()))
            && (this.getReaderName() == null ? other.getReaderName() == null : this.getReaderName().equals(other.getReaderName()))
            && (this.getReaderSex() == null ? other.getReaderSex() == null : this.getReaderSex().equals(other.getReaderSex()))
            && (this.getReaderAge() == null ? other.getReaderAge() == null : this.getReaderAge().equals(other.getReaderAge()))
            && (this.getSaving() == null ? other.getSaving() == null : this.getSaving().equals(other.getSaving()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReaderId() == null) ? 0 : getReaderId().hashCode());
        result = prime * result + ((getReaderName() == null) ? 0 : getReaderName().hashCode());
        result = prime * result + ((getReaderSex() == null) ? 0 : getReaderSex().hashCode());
        result = prime * result + ((getReaderAge() == null) ? 0 : getReaderAge().hashCode());
        result = prime * result + ((getSaving() == null) ? 0 : getSaving().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", readerId=" + readerId +
                ", readerName=" + readerName +
                ", readerSex=" + readerSex +
                ", readerAge=" + readerAge +
                ", saving=" + saving +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
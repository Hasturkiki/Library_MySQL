package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 作者信息
 * @TableName author
 */
@TableName(value ="author")
@Data
public class Author implements Serializable {
    /**
     * 作者id
     */
    @TableId(type = IdType.AUTO)
    private Integer authorId;

    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 作者性别，0为女，1为男，2为保密，默认为2
     */
    private Integer authorSex;

    /**
     * 作者年龄，若作者已经去世则为其去世时年龄，无确切年龄者默认为42
     */
    private Integer authorAge;

    public String getAuthorSex() {
        return switch (authorSex) {
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
        Author other = (Author) that;
        return (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
            && (this.getAuthorSex() == null ? other.getAuthorSex() == null : this.getAuthorSex().equals(other.getAuthorSex()))
            && (this.getAuthorAge() == null ? other.getAuthorAge() == null : this.getAuthorAge().equals(other.getAuthorAge()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getAuthorSex() == null) ? 0 : getAuthorSex().hashCode());
        result = prime * result + ((getAuthorAge() == null) ? 0 : getAuthorAge().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", authorId=" + authorId +
                ", authorName=" + authorName +
                "， authorSex=" + authorSex +
                ", authorAge=" + authorAge +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 共同作者表信息
 * @TableName joint_author_table
 */
@TableName(value ="joint_author_table")
@Data
public class JointAuthorTable implements Serializable {
    /**
     * 该书是否存在共同作者，0为否、>=1则为共同作者表id号，默认为0
     */
    @TableId
    private Integer jointAuthorTableId;

    /**
     * 存在共同作者的书的共同作者表id
     */
    private Integer tableId;

    /**
     * 相同table_id的作者为该joint_author_table_id对应的书的共同作者
     */
    private Integer authorId;

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
        JointAuthorTable other = (JointAuthorTable) that;
        return (this.getJointAuthorTableId() == null ? other.getJointAuthorTableId() == null : this.getJointAuthorTableId().equals(other.getJointAuthorTableId()))
            && (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getJointAuthorTableId() == null) ? 0 : getJointAuthorTableId().hashCode());
        result = prime * result + ((getTableId() == null) ? 0 : getTableId().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", jointAuthorTableId=" + jointAuthorTableId +
                ", tableId=" + tableId +
                ", authorId=" + authorId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
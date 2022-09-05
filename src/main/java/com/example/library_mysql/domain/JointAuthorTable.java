package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 共同作者表信息
 * @TableName joint_author_table
 */
@ApiModel(value = "JointAuthorTable对象", description = "共同作者表")
@TableName(value ="joint_author_table")
@Data
public class JointAuthorTable implements Serializable {
    /**
     * 该书是否存在共同作者，0为否、>=1则为共同作者表id号
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

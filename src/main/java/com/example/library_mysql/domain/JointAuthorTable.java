package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 共同作者表信息
 * @TableName joint_author_table
 */
@ApiModel(value = "JointAuthorTable对象", description = "共同作者表")
@TableName(value ="joint_author_table")
@Data
public class JointAuthorTable implements Serializable {
    /**
     * 共同作者表id：>=1则代表存在共同作者
     */
    @TableId
    @ApiModelProperty("共同作者表id：>=1则代表存在共同作者")
    private Integer jointAuthorTableId;

    /**
     * 共同作者表实例id
     */
    @ApiModelProperty("共同作者表实例id")
    private Integer tableId;

    /**
     * 共同作者表对应的作者id：相同table_id的作者为该joint_author_table_id对应的书的共同作者
     */
    @ApiModelProperty("共同作者表对应的作者id：相同table_id的作者为该joint_author_table_id对应的书的共同作者")
    private Integer authorId;

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

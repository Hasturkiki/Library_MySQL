package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 书籍标签
 * @TableName tag
 */
@ApiModel(value = "Tag对象", description = "标签")
@TableName(value ="tag")
@Data
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("标签id")
    private Integer tagId;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String tagName;

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

    /**
     * 标签下作品数量
     */
    @TableField(exist = false)
    @ApiModelProperty("标签下作品数量")
    private long bookNumber;
}

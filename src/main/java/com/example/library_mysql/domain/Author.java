package com.example.library_mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    /**
     * 作者名下作品数量
     */
    @TableField(exist = false)
    private long bookNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

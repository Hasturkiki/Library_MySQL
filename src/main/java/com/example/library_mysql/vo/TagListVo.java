package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "TagListVo对象", description = "标签列表扩展")
public class TagListVo {
    @ApiModelProperty("标签列表原型")
    private List<Tag> tagList;

    @ApiModelProperty("标签列表页数：pagesNumber=0时代表获取全部标签的列表，pagesNumber=1时代表标签条目数少于单页容量10条，已获取全部标签列表")
    private Long pagesNumber;

    public TagListVo(List<Tag> tagList) {
        this.tagList = tagList;
    }
}

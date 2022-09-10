package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Author;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "AuthorListVo对象", description = "作者列表扩展")
public class AuthorListVo {
    @ApiModelProperty("作者列表原型")
    private List<Author> authorList;

    @ApiModelProperty("作者列表页数：pagesNumber=0时代表获取全部作者的列表，pagesNumber=1时代表作者条目数少于单页容量10条，已获取全部作者列表")
    private Long pagesNumber;

    public AuthorListVo(List<Author> authorList) {
        this.authorList = authorList;
    }
}

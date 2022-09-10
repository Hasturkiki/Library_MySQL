package com.example.library_mysql.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "BookVoListVo对象", description = "书籍扩展列表扩展")
public class BookVoListVo {
    @ApiModelProperty("书籍扩展列表原型")
    private List<BookVo> bookVoList;

    @ApiModelProperty("书籍扩展列表页数：pagesNumber=0时代表获取全部书籍扩展的列表，pagesNumber=1时代表书籍扩展条目数少于单页容量10条，已获取全部书籍扩展列表")
    private Long pagesNumber;

    public BookVoListVo(List<BookVo> bookVoList) {
        this.bookVoList = bookVoList;
    }
}

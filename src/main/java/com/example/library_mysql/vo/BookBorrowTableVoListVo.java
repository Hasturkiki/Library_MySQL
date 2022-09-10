package com.example.library_mysql.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "BookBorrowTableVoListVo对象", description = "借书表扩展列表扩展")
public class BookBorrowTableVoListVo {
    @ApiModelProperty("借书表扩展列表原型")
    private List<BookBorrowTableVo> bookBorrowTableVoList;

    @ApiModelProperty("借书表扩展列表页数：pagesNumber=0时代表获取全部借书表扩展的列表，pagesNumber=1时代表借书表扩展条目数少于单页容量10条，已获取全部借书表扩展列表")
    private Long pagesNumber;

    public BookBorrowTableVoListVo(List<BookBorrowTableVo> bookBorrowTableVoList) {
        this.bookBorrowTableVoList = bookBorrowTableVoList;
    }
}

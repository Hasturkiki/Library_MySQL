package com.example.library_mysql.vo;

import com.example.library_mysql.domain.BookBorrowTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BookBorrowTableVo对象", description = "借书表扩展")
public class BookBorrowTableVo {

    @ApiModelProperty("借书表原型")
    private BookBorrowTable bookBorrowTable;

    @ApiModelProperty("该借书表关联读者名")
    private String readerName;

    @ApiModelProperty("该借书表关联书籍名")
    private String bookName;

    public BookBorrowTableVo(BookBorrowTable bookBorrowTable) {
        this.bookBorrowTable = bookBorrowTable;
    }
}

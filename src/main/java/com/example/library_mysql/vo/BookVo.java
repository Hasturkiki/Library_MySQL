package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BookVo对象", description = "书籍扩展")
public class BookVo {
    @ApiModelProperty("书籍原型")
    private Book book;

    @ApiModelProperty("该书籍关联作者名")
    private String authorName;

    @ApiModelProperty("该书籍关联出版社名")
    private String publishingCompanyName;

    @ApiModelProperty("该书籍关联标签名")
    private String tagName;

    @ApiModelProperty("该书籍共同作者表作生效标志")
    private boolean jointIsAlive;

    public BookVo(Book book) {
        this.book = book;
        this.jointIsAlive = true;
    }
}

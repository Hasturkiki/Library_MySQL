package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Book;
import lombok.Data;

@Data
public class BookVo {
    private Book book;
    private String authorName;
    private String publishingCompanyName;
    private String tagName;

    public BookVo(Book book) {
        this.book = book;
    }
}

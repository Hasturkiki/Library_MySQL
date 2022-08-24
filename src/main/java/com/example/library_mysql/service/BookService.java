package com.example.library_mysql.service;

import com.example.library_mysql.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Service
* @createDate 2022-07-04 11:07:45
*/
public interface BookService extends IService<Book> {
    /**
     * select book by bookId
     * @Param bookId
     * @return Book
     */
    Book selectBookById(@Param("bookId") Integer bookId);
}

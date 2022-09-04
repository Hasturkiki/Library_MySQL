package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Override
    public Book selectBookById(int id) {
        return lambdaQuery().eq(Book::getBookId, id).one();
    }
}





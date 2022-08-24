package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.mapper.BookMapper;
import com.example.library_mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Service实现
* @createDate 2022-07-04 11:07:45
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book selectBookById(Integer bookId) {
        return bookMapper.selectBookById(bookId);
    }
}





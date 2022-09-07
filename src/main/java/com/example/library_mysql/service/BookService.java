package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.BookVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface BookService extends IService<Book> {

    Book selectBookById(int id);

    BookVo selectBookVoById(int id);

    R<List<BookVo>> getBookVoList();

    List<BookVo> searchByName(String searchKey);
}

package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.BookVoListVo;
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

    List<BookVo> searchByName(String searchKey);

    R<BookVoListVo> getAllBookVoListVo();

    R<BookVoListVo> getBookVoListVoByPage(int page);

    R<BookVoListVo> getBookVoListVo(int page, String sortItem, String sortType);
}

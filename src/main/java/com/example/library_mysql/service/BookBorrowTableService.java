package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.BookBorrowTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.BookBorrowTableVoListVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface BookBorrowTableService extends IService<BookBorrowTable> {

    R<List<BookBorrowTable>> getBookBorrowTableList();

    R<BookBorrowTableVoListVo> getAllBookBorrowTableVoListVo();

    R<BookBorrowTableVoListVo> getBookBorrowTableVoListVoByPage(int page);

    R<BookBorrowTableVoListVo> getBookBorrowTableVoListVo(int page, String sortItem, String sortType);
}

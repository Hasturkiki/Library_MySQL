package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.BookBorrowTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.BookBorrowTableVo;
import com.example.library_mysql.vo.BookBorrowTableVoListVo;
import com.example.library_mysql.vo.BookVoListVo;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface BookBorrowTableService extends IService<BookBorrowTable> {

    BookBorrowTableVo selectBookBorrowTableVoById(int id);

    R<List<BookBorrowTable>> getBookBorrowTableList();

    R<BookBorrowTableVoListVo> getAllBookBorrowTableVoListVo();

    R<BookBorrowTableVoListVo> getBookBorrowTableVoListVoByPage(int page);

    R<BookBorrowTableVoListVo> getBookBorrowTableVoListVo(int page, String sortItem, String sortType);

    R<BookBorrowTableVoListVo> BookBorrowTablesByBook(int id);

    R<BookBorrowTableVoListVo> BookBorrowTablesByBookWithCondition(int id, int page, String sortItem, String sortType);

    R<Boolean> deleteBookBorrowTableById(int id, LocalDateTime updateTime);

    boolean deleteBookBorrowTableByOtherId(String sign, int id, LocalDateTime updateTime);

    boolean recoveryByOtherId(String sign, int id, LocalDateTime updateTime);
}

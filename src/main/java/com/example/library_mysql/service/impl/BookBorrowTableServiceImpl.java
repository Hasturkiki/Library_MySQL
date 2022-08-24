package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.BookBorrowTable;
import com.example.library_mysql.mapper.BookBorrowTableMapper;
import com.example.library_mysql.service.BookBorrowTableService;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Service实现
* @createDate 2022-07-04 11:07:45
*/
@Service
public class BookBorrowTableServiceImpl extends ServiceImpl<BookBorrowTableMapper, BookBorrowTable>
    implements BookBorrowTableService {

}





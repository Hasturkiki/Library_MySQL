package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.BookBorrowTable;
import com.example.library_mysql.service.BookBorrowTableService;
import com.example.library_mysql.mapper.BookBorrowTableMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class BookBorrowTableServiceImpl extends ServiceImpl<BookBorrowTableMapper, BookBorrowTable>
    implements BookBorrowTableService{

    @Override
    public R<List<BookBorrowTable>> getBookBorrowTableList() {
        List<BookBorrowTable> bookBorrowTableList = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
        if(bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        return R.success(bookBorrowTableList);
    }
}





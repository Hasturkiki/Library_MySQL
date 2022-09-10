package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.BookBorrowTable;
import com.example.library_mysql.domain.Reader;
import com.example.library_mysql.service.BookBorrowTableService;
import com.example.library_mysql.mapper.BookBorrowTableMapper;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.ReaderService;
import com.example.library_mysql.vo.BookBorrowTableVo;
import com.example.library_mysql.vo.BookBorrowTableVoListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【book_borrow_table(借书表信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class BookBorrowTableServiceImpl extends ServiceImpl<BookBorrowTableMapper, BookBorrowTable>
        implements BookBorrowTableService {

    @Resource
    private ReaderService readerService;

    @Resource
    private BookService bookService;

    @Override
    public R<List<BookBorrowTable>> getBookBorrowTableList() {
        List<BookBorrowTable> bookBorrowTableList = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
        if (bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        return R.success(bookBorrowTableList);
    }

    @Override
    public R<BookBorrowTableVoListVo> getAllBookBorrowTableVoListVo() {
        List<BookBorrowTable> bookBorrowTableList = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
        if (bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        BookBorrowTableVoListVo bookBorrowTableVoListVo = setBookBorrowTableVoListVo(bookBorrowTableList);
        bookBorrowTableVoListVo.setPagesNumber(0L);
        return R.success(bookBorrowTableVoListVo);
    }

    @Override
    public R<BookBorrowTableVoListVo> getBookBorrowTableVoListVoByPage(int page) {
        List<BookBorrowTable> bookBorrowTableList = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getPages();
        if (bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        BookBorrowTableVoListVo bookBorrowTableVoListVo = setBookBorrowTableVoListVo(bookBorrowTableList);
        bookBorrowTableVoListVo.setPagesNumber(pagesNumber);
        return R.success(bookBorrowTableVoListVo);
    }

    private BookBorrowTableVoListVo setBookBorrowTableVoListVo(List<BookBorrowTable> bookBorrowTableList) {
        List<BookBorrowTableVo> bookBorrowTableVoList = new ArrayList<>();
        for (BookBorrowTable bookBorrowTable : bookBorrowTableList) {
            BookBorrowTableVo bookBorrowTableVo = new BookBorrowTableVo(bookBorrowTable);
            Reader reader = readerService.selectReaderById(bookBorrowTable.getReaderId());
            bookBorrowTableVo.setReaderName(reader.getReaderName());
            Book book = bookService.selectBookById(bookBorrowTable.getBookId());
            bookBorrowTableVo.setBookName(book.getBookName());
            bookBorrowTableVoList.add(bookBorrowTableVo);
        }
        return new BookBorrowTableVoListVo(bookBorrowTableVoList);
    }
}





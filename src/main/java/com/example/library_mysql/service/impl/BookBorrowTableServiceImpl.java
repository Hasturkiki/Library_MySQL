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
    public BookBorrowTableVo selectBookBorrowTableVoById(int id) {
        BookBorrowTable bookBorrowTable = lambdaQuery().eq(BookBorrowTable::getBooksBorrowTableId, id).one();
        if (bookBorrowTable == null)
            return null;
        BookBorrowTableVo bookBorrowTableVo = new BookBorrowTableVo(bookBorrowTable);
        Reader reader = readerService.selectReaderById(bookBorrowTable.getReaderId());
        bookBorrowTableVo.setReaderName(reader.getReaderName());
        Book book = bookService.selectBookById(bookBorrowTable.getBookId());
        bookBorrowTableVo.setBookName(book.getBookName());
        return bookBorrowTableVo;
    }

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

    @Override
    public R<BookBorrowTableVoListVo> getBookBorrowTableVoListVo(int page, String sortItem, String sortType) {
        List<BookBorrowTable> bookBorrowTableList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "booksBorrowTableId" -> lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().orderByAsc(BookBorrowTable::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().orderByAsc(BookBorrowTable::getBookId).page(new Page<>(page, 10)).getRecords();
                case "isBorrowing" -> lambdaQuery().orderByAsc(BookBorrowTable::getIsBorrowing).page(new Page<>(page, 10)).getRecords();
                case "borrowTime" -> lambdaQuery().orderByAsc(BookBorrowTable::getBorrowTime).page(new Page<>(page, 10)).getRecords();
                case "backTime" -> lambdaQuery().orderByAsc(BookBorrowTable::getBackTime).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
            };
            case "desc" -> switch (sortItem) {
                case "booksBorrowTableId" -> lambdaQuery().orderByDesc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().orderByDesc(BookBorrowTable::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().orderByDesc(BookBorrowTable::getBookId).page(new Page<>(page, 10)).getRecords();
                case "isBorrowing" -> lambdaQuery().orderByDesc(BookBorrowTable::getIsBorrowing).page(new Page<>(page, 10)).getRecords();
                case "borrowTime" -> lambdaQuery().orderByDesc(BookBorrowTable::getBorrowTime).page(new Page<>(page, 10)).getRecords();
                case "backTime" -> lambdaQuery().orderByDesc(BookBorrowTable::getBackTime).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
            };
            default -> lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getPages();

        if (bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        BookBorrowTableVoListVo bookBorrowTableVoListVo = setBookBorrowTableVoListVo(bookBorrowTableList);

        bookBorrowTableVoListVo.setPagesNumber(pagesNumber);
        return R.success(bookBorrowTableVoListVo);
    }

    @Override
    public R<BookBorrowTableVoListVo> BookBorrowTablesByBook(int id) {
        List<BookBorrowTable> bookBorrowTableList = lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
        if (bookBorrowTableList.isEmpty()) {
            return R.error("无借书表数据");
        }
        BookBorrowTableVoListVo bookBorrowTableVoListVo = setBookBorrowTableVoListVo(bookBorrowTableList);
        bookBorrowTableVoListVo.setPagesNumber(0L);
        return R.success(bookBorrowTableVoListVo);
    }

    @Override
    public R<BookBorrowTableVoListVo> BookBorrowTablesByBookWithCondition(int id, int page, String sortItem, String sortType) {
        List<BookBorrowTable> bookBorrowTableList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "booksBorrowTableId" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBookId).page(new Page<>(page, 10)).getRecords();
                case "isBorrowing" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getIsBorrowing).page(new Page<>(page, 10)).getRecords();
                case "borrowTime" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBorrowTime).page(new Page<>(page, 10)).getRecords();
                case "backTime" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBackTime).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
            };
            case "desc" -> switch (sortItem) {
                case "booksBorrowTableId" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getBookId).page(new Page<>(page, 10)).getRecords();
                case "isBorrowing" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getIsBorrowing).page(new Page<>(page, 10)).getRecords();
                case "borrowTime" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getBorrowTime).page(new Page<>(page, 10)).getRecords();
                case "backTime" -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByDesc(BookBorrowTable::getBackTime).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).list();
            };
            default -> lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().eq(BookBorrowTable::getBookId, id).orderByAsc(BookBorrowTable::getBooksBorrowTableId).page(new Page<>(page, 10)).getPages();

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





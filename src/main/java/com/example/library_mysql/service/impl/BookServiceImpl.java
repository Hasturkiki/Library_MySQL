package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.*;
import com.example.library_mysql.service.*;
import com.example.library_mysql.mapper.BookMapper;
import com.example.library_mysql.vo.BookVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【book(书籍信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements BookService {

    @Resource
    private AuthorService authorService;
    @Resource
    private PublishingCompanyService publishingCompanyService;
    @Resource
    private TagService tagService;

    @Override
    public Book selectBookById(int id) {
        return lambdaQuery().eq(Book::getBookId, id).one();
    }

    @Override
    public BookVo selectBookVoById(int id) {
        Book book = selectBookById(id);
        BookVo bookVo = new BookVo(book);
        if (book != null) {
            Author author = authorService.selectAuthorById(book.getAuthorId());
            bookVo.setAuthorName(author.getAuthorName());
            PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(book.getPublishingCompanyId());
            bookVo.setPublishingCompanyName(publishingCompany.getPublishingCompanyName());
            Tag tag = tagService.selectTagById(book.getTagId());
            bookVo.setTagName(tag.getTagName());
        }
        return bookVo;
    }

    @Override
    public R<List<BookVo>> getBookVoList() {
        List<Book> bookList = lambdaQuery().orderByAsc(Book::getBookId).list();
        if (bookList.isEmpty()) {
            return R.error("无书籍数据");
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        return R.success(bookVoList);
    }

    @Override
    public List<BookVo> searchByName(String searchKey) {
        List<Book> bookList = lambdaQuery().like(Book::getBookName, searchKey).list();
        if(bookList.isEmpty()) {
            return null;
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        return bookVoList;
    }
}





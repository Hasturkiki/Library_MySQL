package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.mapper.BookMapper;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.PublishingCompanyService;
import com.example.library_mysql.service.TagService;
import com.example.library_mysql.vo.BookVo;
import com.example.library_mysql.vo.BookVoListVo;
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
    public BookVo selectBookVoByName(String name) {
        Book book = lambdaQuery().eq(Book::getBookName, name).one();
        if (book == null)
            return null;
        BookVo bookVo = new BookVo(book);
        Author author = authorService.selectAuthorById(book.getAuthorId());
        bookVo.setAuthorName(author.getAuthorName());
        PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(book.getPublishingCompanyId());
        bookVo.setPublishingCompanyName(publishingCompany.getPublishingCompanyName());
        Tag tag = tagService.selectTagById(book.getTagId());
        bookVo.setTagName(tag.getTagName());
        return bookVo;
    }

    @Override
    public BookVo selectBookVoById(int id) {
        Book book = selectBookById(id);
        if (book == null)
            return null;
        BookVo bookVo = new BookVo(book);
        Author author = authorService.selectAuthorById(book.getAuthorId());
        bookVo.setAuthorName(author.getAuthorName());
        PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(book.getPublishingCompanyId());
        bookVo.setPublishingCompanyName(publishingCompany.getPublishingCompanyName());
        Tag tag = tagService.selectTagById(book.getTagId());
        bookVo.setTagName(tag.getTagName());
        return bookVo;
    }

    @Override
    public List<BookVo> searchByName(String searchKey) {
        List<Book> bookList = lambdaQuery().like(Book::getBookName, searchKey).list();
        if (bookList.isEmpty()) {
            return null;
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        return bookVoList;
    }

    @Override
    public R<BookVoListVo> getAllBookVoListVo() {
        List<Book> bookList = lambdaQuery().orderByAsc(Book::getBookId).list();
        if (bookList.isEmpty()) {
            return R.error("无书籍数据");
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        BookVoListVo bookVoListVo = new BookVoListVo(bookVoList);
        bookVoListVo.setPagesNumber(0L);
        return R.success(bookVoListVo);
    }

    @Override
    public R<BookVoListVo> getBookVoListVoByPage(int page) {
        List<Book> bookList = lambdaQuery().orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
        return getR_BookVoListVoByPage(page, bookList);
    }

    @Override
    public R<BookVoListVo> getBookVoListVo(int page, String sortItem, String sortType) {
        List<Book> bookList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "bookId" -> lambdaQuery().orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().orderByAsc(Book::getBookName).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> lambdaQuery().orderByAsc(Book::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "ibsn" -> lambdaQuery().orderByAsc(Book::getIbsn).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> lambdaQuery().orderByAsc(Book::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> lambdaQuery().orderByAsc(Book::getTagId).page(new Page<>(page, 10)).getRecords();
                case "quantity" -> lambdaQuery().orderByAsc(Book::getQuantity).page(new Page<>(page, 10)).getRecords();
                case "price" -> lambdaQuery().orderByAsc(Book::getPrice).page(new Page<>(page, 10)).getRecords();
                case "isBeingBorrowed" -> lambdaQuery().orderByAsc(Book::getIsBeingBorrowed).page(new Page<>(page, 10)).getRecords();
                case "publicationDate" -> lambdaQuery().orderByAsc(Book::getPublicationDate).page(new Page<>(page, 10)).getRecords();
                case "jointAuthorTableId" -> lambdaQuery().orderByAsc(Book::getJointAuthorTableId).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Book::getBookId).list();
            };
            case "desc" -> switch (sortItem) {
                case "bookId" -> lambdaQuery().orderByDesc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> lambdaQuery().orderByDesc(Book::getBookName).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> lambdaQuery().orderByDesc(Book::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "ibsn" -> lambdaQuery().orderByDesc(Book::getIbsn).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> lambdaQuery().orderByDesc(Book::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> lambdaQuery().orderByDesc(Book::getTagId).page(new Page<>(page, 10)).getRecords();
                case "quantity" -> lambdaQuery().orderByDesc(Book::getQuantity).page(new Page<>(page, 10)).getRecords();
                case "price" -> lambdaQuery().orderByDesc(Book::getPrice).page(new Page<>(page, 10)).getRecords();
                case "isBeingBorrowed" -> lambdaQuery().orderByDesc(Book::getIsBeingBorrowed).page(new Page<>(page, 10)).getRecords();
                case "publicationDate" -> lambdaQuery().orderByDesc(Book::getPublicationDate).page(new Page<>(page, 10)).getRecords();
                case "jointAuthorTableId" -> lambdaQuery().orderByDesc(Book::getJointAuthorTableId).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Book::getBookId).list();
            };
            default -> lambdaQuery().orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
        };
        return getR_BookVoListVoByPage(page, bookList);
    }

    @Override
    public R<BookVoListVo> selectBooksByAuthor(int id) {
        return selectBooks(lambdaQuery().eq(Book::getAuthorId, id));
    }

    @Override
    public R<BookVoListVo> selectBooksByAuthorWithCondition(int id, int page, String sortItem, String sortType) {
        return selectBooksWithCondition(page, sortItem, sortType, lambdaQuery().eq(Book::getAuthorId, id));
    }

    @Override
    public R<BookVoListVo> selectBooksByPublishingCompany(int id) {
        return selectBooks(lambdaQuery().eq(Book::getPublishingCompanyId, id));
    }

    @Override
    public R<BookVoListVo> selectBooksByPublishingCompanyWithCondition(int id, int page, String sortItem, String sortType) {
        return selectBooksWithCondition(page, sortItem, sortType, lambdaQuery().eq(Book::getPublishingCompanyId, id));
    }

    @Override
    public R<BookVoListVo> selectBooksByTag(int id) {
        return selectBooks(lambdaQuery().eq(Book::getTagId, id));
    }

    @Override
    public R<BookVoListVo> selectBooksByTagWithCondition(int id, int page, String sortItem, String sortType) {
        return selectBooksWithCondition(page, sortItem, sortType, lambdaQuery().eq(Book::getTagId, id));
    }

    private R<BookVoListVo> getR_BookVoListVoByPage(int page, List<Book> bookList) {
        if (bookList.isEmpty()) {
            return R.error("无书籍数据");
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        long pagesNumber = lambdaQuery().orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getPages();
        BookVoListVo bookVoListVo = new BookVoListVo(bookVoList);
        bookVoListVo.setPagesNumber(pagesNumber);
        return R.success(bookVoListVo);
    }

    private R<BookVoListVo> selectBooks(LambdaQueryChainWrapper<Book> eq) {
        List<Book> bookList = eq.list();
        if (bookList.isEmpty()) {
            return R.error("无书籍数据");
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        BookVoListVo bookVoListVo = new BookVoListVo(bookVoList);
        bookVoListVo.setPagesNumber(0L);
        return R.success(bookVoListVo);
    }

    private R<BookVoListVo> selectBooksWithCondition(int page, String sortItem, String sortType, LambdaQueryChainWrapper<Book> eq) {
        List<Book> bookList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "bookId" -> eq.orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> eq.orderByAsc(Book::getBookName).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> eq.orderByAsc(Book::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "ibsn" -> eq.orderByAsc(Book::getIbsn).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> eq.orderByAsc(Book::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> eq.orderByAsc(Book::getTagId).page(new Page<>(page, 10)).getRecords();
                case "quantity" -> eq.orderByAsc(Book::getQuantity).page(new Page<>(page, 10)).getRecords();
                case "price" -> eq.orderByAsc(Book::getPrice).page(new Page<>(page, 10)).getRecords();
                case "isBeingBorrowed" -> eq.orderByAsc(Book::getIsBeingBorrowed).page(new Page<>(page, 10)).getRecords();
                case "publicationDate" -> eq.orderByAsc(Book::getPublicationDate).page(new Page<>(page, 10)).getRecords();
                case "jointAuthorTableId" -> eq.orderByAsc(Book::getJointAuthorTableId).page(new Page<>(page, 10)).getRecords();
                default -> eq.orderByAsc(Book::getBookId).list();
            };
            case "desc" -> switch (sortItem) {
                case "bookId" -> eq.orderByDesc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
                case "bookName" -> eq.orderByDesc(Book::getBookName).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> eq.orderByDesc(Book::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "ibsn" -> eq.orderByDesc(Book::getIbsn).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> eq.orderByDesc(Book::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> eq.orderByDesc(Book::getTagId).page(new Page<>(page, 10)).getRecords();
                case "quantity" -> eq.orderByDesc(Book::getQuantity).page(new Page<>(page, 10)).getRecords();
                case "price" -> eq.orderByDesc(Book::getPrice).page(new Page<>(page, 10)).getRecords();
                case "isBeingBorrowed" -> eq.orderByDesc(Book::getIsBeingBorrowed).page(new Page<>(page, 10)).getRecords();
                case "publicationDate" -> eq.orderByDesc(Book::getPublicationDate).page(new Page<>(page, 10)).getRecords();
                case "jointAuthorTableId" -> eq.orderByDesc(Book::getJointAuthorTableId).page(new Page<>(page, 10)).getRecords();
                default -> eq.orderByAsc(Book::getBookId).list();
            };
            default -> eq.orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getRecords();
        };
        if (bookList.isEmpty()) {
            return R.error("无书籍数据");
        }
        List<BookVo> bookVoList = new ArrayList<>();
        for (Book book : bookList) {
            bookVoList.add(selectBookVoById(book.getBookId()));
        }
        long pagesNumber = eq.orderByAsc(Book::getBookId).page(new Page<>(page, 10)).getPages();
        BookVoListVo bookVoListVo = new BookVoListVo(bookVoList);
        bookVoListVo.setPagesNumber(pagesNumber);
        return R.success(bookVoListVo);
    }
}

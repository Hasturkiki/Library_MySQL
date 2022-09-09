package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.JointAuthorTable;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.mapper.AuthorMapper;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.JointAuthorTableService;
import com.example.library_mysql.vo.AuthorListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【author(作者信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
        implements AuthorService {

    @Resource
    private BookService bookService;

    @Resource
    private JointAuthorTableService jointAuthorTableService;

    @Override
    public Author selectAuthorById(int id) {
        Author author = lambdaQuery().eq(Author::getAuthorId, id).one();
        long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, id).eq(Book::getJointAuthorTableId, 0).count();
        bookNumber += jointAuthorTableService.lambdaQuery().eq(JointAuthorTable::getAuthorId, id).count();
        if (author != null)
            author.setBookNumber(bookNumber);
        return author;
    }

    @Override
    public List<Author> searchByName(String searchKey) {
        List<Author> authorList = lambdaQuery().like(Author::getAuthorName, searchKey).list();
        if (authorList.isEmpty()) {
            return null;
        }
        setBookNumber(authorList);
        return authorList;
    }

    @Override
    public R<AuthorListVo> getAuthorList() {
        List<Author> authorList = lambdaQuery().orderByAsc(Author::getAuthorId).list();
        if (authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        setBookNumber(authorList);
        AuthorListVo authorListVo = new AuthorListVo(authorList);
        authorListVo.setPagesNumber(0L);
        return R.success(authorListVo);
    }

    @Override
    public R<AuthorListVo> getAuthorListByPage(int page) {
        List<Author> authorList = lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page,10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page,10)).getPages();
        if (authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        setBookNumber(authorList);
        AuthorListVo authorListVo = new AuthorListVo(authorList);
        authorListVo.setPagesNumber(pagesNumber);
        return R.success(authorListVo);
    }

    private void setBookNumber(List<Author> authorList) {
        for (Author author : authorList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, author.getAuthorId()).eq(Book::getJointAuthorTableId, 0).count();
            bookNumber += jointAuthorTableService.lambdaQuery().eq(JointAuthorTable::getAuthorId, author.getAuthorId()).count();
            author.setBookNumber(bookNumber);
        }
    }
}





package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.mapper.AuthorMapper;
import com.example.library_mysql.service.BookService;
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
    implements AuthorService{

    @Resource
    private BookService bookService;

    @Override
    public Author selectAuthorById(int id) {
        Author author = lambdaQuery().eq(Author::getAuthorId, id).one();
        long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, id).count();
        if (author != null)
            author.setBookNumber(bookNumber);
        return author;
    }

    @Override
    public R<List<Author>> getAuthorList() {
        List<Author> authorList = lambdaQuery().orderByAsc(Author::getAuthorId).list();
        if(authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        return R.success(authorList);
    }
}





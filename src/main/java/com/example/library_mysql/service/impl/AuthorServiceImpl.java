package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【author(作者信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
    implements AuthorService{

    @Override
    public Author selectAuthorById(int id) {
        return lambdaQuery().eq(Author::getAuthorId, id).one();
    }
}





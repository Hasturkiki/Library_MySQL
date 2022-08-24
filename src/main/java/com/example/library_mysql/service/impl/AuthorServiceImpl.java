package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.mapper.AuthorMapper;
import com.example.library_mysql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【author(作者信息)】的数据库操作Service实现
* @createDate 2022-07-04 11:03:31
*/
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
    implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Author selectAuthorById(Integer authorId) {
        return authorMapper.selectAuthorById(authorId);
    }
}





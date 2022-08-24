package com.example.library_mysql.service;

import com.example.library_mysql.domain.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【author(作者信息)】的数据库操作Service
* @createDate 2022-07-04 11:03:31
*/
public interface AuthorService extends IService<Author> {
    /**
     * select author by authorId
     * @Param authorId
     * @return Author
     */
    Author selectAuthorById(@Param("authorId") Integer authorId);
}

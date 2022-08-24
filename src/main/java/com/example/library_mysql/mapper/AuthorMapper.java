package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【author(作者信息)】的数据库操作Mapper
* @createDate 2022-07-04 11:03:31
* @Entity generator.domain.Author
*/
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {
    /**
     * select author by authorId
     * @Param authorId
     * @return Author
     */
    Author selectAuthorById(@Param("authorId") Integer authorId);
}





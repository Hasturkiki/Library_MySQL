package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Mapper
* @createDate 2022-07-04 11:07:45
* @Entity generator.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    /**
     * select book by bookId
     * @Param bookId
     * @return Book
     */
    Book selectBookById(@Param("bookId") Integer bookId);
}





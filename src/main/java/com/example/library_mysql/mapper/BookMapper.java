package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
* @author Hastur kiki
* @description 针对表【book(书籍信息)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    public boolean recoveryById(Integer id);

    public boolean recoveryByAuthorId(Integer id, LocalDateTime updateTime);

    public boolean recoveryByPublishingCompanyId(Integer id, LocalDateTime updateTime);

    public boolean recoveryByTagId(Integer id, LocalDateTime updateTime);

    public boolean recoveryByUpdateTime(LocalDateTime updateTime);
}


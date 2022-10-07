package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.BookBorrowTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.BookBorrowTable
*/
@Mapper
public interface BookBorrowTableMapper extends BaseMapper<BookBorrowTable> {
    public boolean recoveryById(Integer id);

    public boolean recoveryByBookId(int id, LocalDateTime updateTime);

    public boolean recoveryByReaderId(int id, LocalDateTime updateTime);
}





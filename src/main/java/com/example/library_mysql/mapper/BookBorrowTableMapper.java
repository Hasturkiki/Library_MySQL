package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.BookBorrowTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Hastur kiki
* @description 针对表【book_borrow_table(借书表信息)】的数据库操作Mapper
* @createDate 2022-07-04 11:07:45
* @Entity generator.domain.BookBorrowTable
*/
@Mapper
public interface BookBorrowTableMapper extends BaseMapper<BookBorrowTable> {

}





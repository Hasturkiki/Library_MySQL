package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Reader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【reader(读者信息)】的数据库操作Mapper
* @createDate 2022-07-04 11:07:45
* @Entity generator.domain.Reader
*/
@Mapper
public interface ReaderMapper extends BaseMapper<Reader> {
    /**
     * select reader by readerId
     * @Param readerId
     * @return Reader
     */
    Reader selectReaderById(@Param("readerId") Integer readerId);
}





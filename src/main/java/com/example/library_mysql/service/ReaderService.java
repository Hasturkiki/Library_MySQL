package com.example.library_mysql.service;

import com.example.library_mysql.domain.Reader;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【reader(读者信息)】的数据库操作Service
* @createDate 2022-07-04 11:07:45
*/
public interface ReaderService extends IService<Reader> {
    /**
     * select reader by readerId
     * @Param readerId
     * @return Reader
     */
    Reader selectReaderById(@Param("readerId") Integer readerId);
}

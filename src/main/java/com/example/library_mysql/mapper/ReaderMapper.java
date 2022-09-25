package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Reader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Hastur kiki
* @description 针对表【reader(读者信息)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.Reader
*/
@Mapper
public interface ReaderMapper extends BaseMapper<Reader> {
    public Integer recoveryById(Integer id);
}





package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Reader;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.ReaderListVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【reader(读者信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface ReaderService extends IService<Reader> {

    Reader selectReaderById(int id);

    Reader selectReaderByName(String key);

    List<Reader> searchByName(String searchKey);

    R<ReaderListVo> getAllReaderListVo();

    R<ReaderListVo> getReaderListVoByPage(int page);

    R<ReaderListVo> getReaderListVo(int page, String sortItem, String sortType);

    R<Boolean> deleteReaderById(int id);
}

package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Reader;
import com.example.library_mysql.mapper.ReaderMapper;
import com.example.library_mysql.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【reader(读者信息)】的数据库操作Service实现
* @createDate 2022-07-04 11:07:45
*/
@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader>
    implements ReaderService{

    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public Reader selectReaderById(Integer readerId) {
        return readerMapper.selectReaderById(readerId);
    }
}




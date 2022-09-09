package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.AuthorListVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【author(作者信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface AuthorService extends IService<Author> {

    Author selectAuthorById(int id);

    List<Author> searchByName(String searchKey);

    R<AuthorListVo> getAuthorList();

    R<AuthorListVo> getAuthorListByPage(int i);
}

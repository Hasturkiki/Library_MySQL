package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.AuthorListVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【author(作者信息)】的数据库操作Service
 * @createDate 2022-09-04 16:34:36
 */
public interface AuthorService extends IService<Author> {

    Author selectAuthorById(int id);

    Author selectAuthorByName(String key);

    List<Author> searchByName(String searchKey);

    R<AuthorListVo> getAllAuthorListVo();

    R<AuthorListVo> getAuthorListVoByPage(int page);

    R<AuthorListVo> getAuthorListVo(int page, String sortItem, String sortType);

    R<Boolean> deleteAuthorById(int id, LocalDateTime updateTime);
}

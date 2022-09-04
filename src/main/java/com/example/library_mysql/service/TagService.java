package com.example.library_mysql.service;

import com.example.library_mysql.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface TagService extends IService<Tag> {

    Tag selectTagById(int id);
}

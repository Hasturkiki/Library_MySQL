package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.TagListVo;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface TagService extends IService<Tag> {

    Tag selectTagById(int id);

    Tag selectTagByName(String key);

    List<Tag> searchByName(String searchKey);

    R<TagListVo> getAllTagListVo();

    R<TagListVo> getTagListVoByPage(int page);

    R<TagListVo> getTagListVo(int page, String sortItem, String sortType);

    R<Boolean> deleteTagById(int id, LocalDateTime updateTime);
}

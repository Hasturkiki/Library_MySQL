package com.example.library_mysql.service;

import com.example.library_mysql.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Service
* @createDate 2022-07-04 11:07:45
*/
public interface TagService extends IService<Tag> {
    /**
     * select tag by tagId
     * @Param tagId
     * @return Tag
     */
    Tag selectTagById(@Param("tagId") Integer tagId);
}

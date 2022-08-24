package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Mapper
* @createDate 2022-07-04 11:07:45
* @Entity generator.domain.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * select tag by tagId
     * @Param tagId
     * @return Tag
     */
    Tag selectTagById(@Param("tagId") Integer tagId);
}





package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}





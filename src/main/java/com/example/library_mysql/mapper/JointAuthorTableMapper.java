package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.JointAuthorTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Hastur kiki
* @description 针对表【joint_author_table(共同作者表信息)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.JointAuthorTable
*/
@Mapper
public interface JointAuthorTableMapper extends BaseMapper<JointAuthorTable> {
    public Integer recoveryById(Integer id);
}





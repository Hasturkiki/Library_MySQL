package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.PublishingCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Mapper
* @createDate 2022-09-04 16:34:36
* @Entity com.example.library_mysql.domain.PublishingCompany
*/
@Mapper
public interface PublishingCompanyMapper extends BaseMapper<PublishingCompany> {

}





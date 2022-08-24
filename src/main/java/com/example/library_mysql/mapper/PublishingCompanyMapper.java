package com.example.library_mysql.mapper;

import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.PublishingCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Mapper
* @createDate 2022-07-04 11:07:45
* @Entity generator.domain.PublishingCompany
*/
@Mapper
public interface PublishingCompanyMapper extends BaseMapper<PublishingCompany> {
    /**
     * select publishingCompany by publishingCompanyId
     * @Param publishingCompanyId
     * @return PublishingCompany
     */
    PublishingCompany selectPublishingCompanyById(@Param("publishingCompanyId") Integer publishingCompanyId);
}





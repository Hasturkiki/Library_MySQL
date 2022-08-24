package com.example.library_mysql.service;

import com.example.library_mysql.domain.PublishingCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service
* @createDate 2022-07-04 11:07:45
*/
public interface PublishingCompanyService extends IService<PublishingCompany> {
    /**
     * select publishingCompany by publishingCompanyId
     * @Param publishingCompanyId
     * @return PublishingCompany
     */
    PublishingCompany selectPublishingCompanyById(@Param("publishingCompanyId") Integer publishingCompanyId);
}

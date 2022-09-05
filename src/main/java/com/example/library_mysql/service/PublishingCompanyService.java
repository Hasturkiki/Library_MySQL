package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.PublishingCompany;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface PublishingCompanyService extends IService<PublishingCompany> {

    PublishingCompany selectPublishingCompanyById(int id);

    R<List<PublishingCompany>> getPublishingCompanyList();
}

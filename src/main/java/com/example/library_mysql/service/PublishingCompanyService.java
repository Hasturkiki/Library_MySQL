package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.PublishingCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.PublishingCompanyListVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface PublishingCompanyService extends IService<PublishingCompany> {

    PublishingCompany selectPublishingCompanyById(int id);

    PublishingCompany selectPublishingCompanyByName(String key);

    List<PublishingCompany> searchByName(String searchKey);

    R<PublishingCompanyListVo> getAllPublishingCompanyListVo();

    R<PublishingCompanyListVo> getPublishingCompanyListVoByPage(int page);

    R<PublishingCompanyListVo> getPublishingCompanyListVo(int page, String sortItem, String sortType);
}

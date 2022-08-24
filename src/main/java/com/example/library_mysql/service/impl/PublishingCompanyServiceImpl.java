package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.mapper.PublishingCompanyMapper;
import com.example.library_mysql.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service实现
* @createDate 2022-07-04 11:07:45
*/
@Service
public class PublishingCompanyServiceImpl extends ServiceImpl<PublishingCompanyMapper, PublishingCompany>
    implements PublishingCompanyService{

    @Autowired
    private PublishingCompanyMapper publishingCompanyMapper;

    @Override
    public PublishingCompany selectPublishingCompanyById(Integer publishingCompanyId) {
        return publishingCompanyMapper.selectPublishingCompanyById(publishingCompanyId);
    }
}





package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.mapper.PublishingCompanyMapper;
import com.example.library_mysql.service.PublishingCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class PublishingCompanyServiceImpl extends ServiceImpl<PublishingCompanyMapper, PublishingCompany>
    implements PublishingCompanyService{

    @Override
    public PublishingCompany selectPublishingCompanyById(int id) {
        return lambdaQuery().eq(PublishingCompany::getPublishingCompanyId, id).one();
    }

    @Override
    public R<List<PublishingCompany>> getPublishingCompanyList() {
        List<PublishingCompany> publishingCompanyList = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).list();
        if(publishingCompanyList.isEmpty()) {
            return R.error("无出版社数据");
        }
        return R.success(publishingCompanyList);
    }
}





package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.mapper.PublishingCompanyMapper;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.PublishingCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【publishing_company(出版社信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class PublishingCompanyServiceImpl extends ServiceImpl<PublishingCompanyMapper, PublishingCompany>
    implements PublishingCompanyService{

    @Resource
    private BookService bookService;

    @Override
    public PublishingCompany selectPublishingCompanyById(int id) {
        PublishingCompany publishingCompany = lambdaQuery().eq(PublishingCompany::getPublishingCompanyId, id).one();
        long bookNumber = bookService.lambdaQuery().eq(Book::getPublishingCompanyId, id).count();
        if (publishingCompany != null)
            publishingCompany.setBookNumber(bookNumber);
        return publishingCompany;
    }

    @Override
    public R<List<PublishingCompany>> getPublishingCompanyList() {
        List<PublishingCompany> publishingCompanyList = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).list();
        if(publishingCompanyList.isEmpty()) {
            return R.error("无出版社数据");
        }
        for(PublishingCompany publishingCompany:publishingCompanyList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getPublishingCompanyId, publishingCompany.getPublishingCompanyId()).count();
            publishingCompany.setBookNumber(bookNumber);
        }
        return R.success(publishingCompanyList);
    }
}





package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.mapper.PublishingCompanyMapper;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.PublishingCompanyService;
import com.example.library_mysql.vo.PublishingCompanyListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【publishing_company(出版社信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class PublishingCompanyServiceImpl extends ServiceImpl<PublishingCompanyMapper, PublishingCompany>
        implements PublishingCompanyService {

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
    public PublishingCompany selectPublishingCompanyByName(String name) {
        PublishingCompany publishingCompany = lambdaQuery().eq(PublishingCompany::getPublishingCompanyName, name).one();
        if (publishingCompany != null) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getPublishingCompanyId, publishingCompany.getPublishingCompanyId()).count();
            publishingCompany.setBookNumber(bookNumber);
        }
        return publishingCompany;
    }

    @Override
    public List<PublishingCompany> searchByName(String searchKey) {
        List<PublishingCompany> publishingCompanyList = lambdaQuery().like(PublishingCompany::getPublishingCompanyName, searchKey).list();
        if (publishingCompanyList.isEmpty()) {
            return null;
        }
        setBookNumber(publishingCompanyList);
        return publishingCompanyList;
    }

    @Override
    public R<PublishingCompanyListVo> getAllPublishingCompanyListVo() {
        List<PublishingCompany> publishingCompanyList = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).list();
        if (publishingCompanyList.isEmpty()) {
            return R.error("无出版社数据");
        }
        setBookNumber(publishingCompanyList);
        PublishingCompanyListVo publishingCompanyListVo = new PublishingCompanyListVo(publishingCompanyList);
        publishingCompanyListVo.setPagesNumber(0L);
        return R.success(publishingCompanyListVo);
    }

    @Override
    public R<PublishingCompanyListVo> getPublishingCompanyListVoByPage(int page) {
        List<PublishingCompany> publishingCompanyList = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getPages();
        if (publishingCompanyList.isEmpty()) {
            return R.error("无出版社数据");
        }
        setBookNumber(publishingCompanyList);
        PublishingCompanyListVo publishingCompanyListVo = new PublishingCompanyListVo(publishingCompanyList);
        publishingCompanyListVo.setPagesNumber(pagesNumber);
        return R.success(publishingCompanyListVo);
    }

    @Override
    public R<PublishingCompanyListVo> getPublishingCompanyListVo(int page, String sortItem, String sortType) {
        List<PublishingCompany> publishingCompanyList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "publishingCompanyId" -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyName).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyTelephoneNumber" -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyTelephoneNumber).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyAddress" -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyAddress).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).list();
            };
            case "desc" -> switch (sortItem) {
                case "publishingCompanyId" -> lambdaQuery().orderByDesc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyName" -> lambdaQuery().orderByDesc(PublishingCompany::getPublishingCompanyName).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyTelephoneNumber" -> lambdaQuery().orderByDesc(PublishingCompany::getPublishingCompanyTelephoneNumber).page(new Page<>(page, 10)).getRecords();
                case "publishingCompanyAddress" -> lambdaQuery().orderByDesc(PublishingCompany::getPublishingCompanyAddress).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).list();
            };
            default -> lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().orderByAsc(PublishingCompany::getPublishingCompanyId).page(new Page<>(page, 10)).getPages();

        if (publishingCompanyList.isEmpty()) {
            return R.error("无出版社数据");
        }
        setBookNumber(publishingCompanyList);
        if (sortItem.equals("bookNumber"))
            if (sortType.equals("asc"))
                publishingCompanyList = publishingCompanyList.stream().sorted(Comparator.comparing(PublishingCompany::getBookNumber)).skip((page - 1) * 10L).limit(10).toList();
            else if (sortType.equals("desc"))
                publishingCompanyList = publishingCompanyList.stream().sorted(Comparator.comparing(PublishingCompany::getBookNumber).reversed()).skip((page - 1) * 10L).limit(10).toList();

        PublishingCompanyListVo publishingCompanyListVo = new PublishingCompanyListVo(publishingCompanyList);
        publishingCompanyListVo.setPagesNumber(pagesNumber);
        return R.success(publishingCompanyListVo);
    }

    @Override
    public R<Boolean> deletePublishingCompanyById(int id, LocalDateTime updateTime) {
        PublishingCompany publishingCompany = lambdaQuery().eq(PublishingCompany::getPublishingCompanyId, id).one();
        if (publishingCompany == null) {
            return R.error("出版社信息删除失败（不存在该出版社）");
        } else {
            if (bookService.deleteBookByOtherId("publishingCompanyId", id, updateTime)) {
                publishingCompany.setUpdateTime(updateTime);
                updateById(publishingCompany);
                if (removeById(id))
                    return R.success(true);
                else
                    return R.error("出版社信息删除失败");
            } else {
                return R.error("关联信息删除失败（书籍）");
            }
        }
    }

    private void setBookNumber(List<PublishingCompany> publishingCompanyList) {
        for (PublishingCompany publishingCompany : publishingCompanyList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getPublishingCompanyId, publishingCompany.getPublishingCompanyId()).count();
            publishingCompany.setBookNumber(bookNumber);
        }
    }
}

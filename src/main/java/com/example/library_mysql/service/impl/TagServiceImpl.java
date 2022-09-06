package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.TagService;
import com.example.library_mysql.mapper.TagMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【tag(书籍标签)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Resource
    private BookService bookService;

    @Override
    public Tag selectTagById(int id) {
        Tag tag = lambdaQuery().eq(Tag::getTagId, id).one();
        long bookNumber = bookService.lambdaQuery().eq(Book::getTagId, id).count();
        if (tag != null)
            tag.setBookNumber(bookNumber);
        return tag;
    }

    @Override
    public R<List<Tag>> getTagList() {
        List<Tag> tagList = lambdaQuery().orderByAsc(Tag::getTagId).list();
        if(tagList.isEmpty()) {
            return R.error("无标签数据");
        }
        for (Tag tag : tagList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getTagId, tag.getTagId()).count();
            tag.setBookNumber(bookNumber);
        }
        return R.success(tagList);
    }
}





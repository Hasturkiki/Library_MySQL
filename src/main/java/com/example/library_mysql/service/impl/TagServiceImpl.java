package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.service.TagService;
import com.example.library_mysql.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【tag(书籍标签)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public Tag selectTagById(int id) {
        return lambdaQuery().eq(Tag::getTagId, id).one();
    }

    @Override
    public R<List<Tag>> getTagList() {
        List<Tag> tagList = lambdaQuery().orderByAsc(Tag::getTagId).list();
        if(tagList.isEmpty()) {
            return R.error("无标签数据");
        }
        return R.success(tagList);
    }
}





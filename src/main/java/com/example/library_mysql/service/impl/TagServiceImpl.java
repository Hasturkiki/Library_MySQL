package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.mapper.TagMapper;
import com.example.library_mysql.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Hastur kiki
* @description 针对表【tag(书籍标签)】的数据库操作Service实现
* @createDate 2022-07-04 11:07:45
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag selectTagById(Integer tagId) {
        return tagMapper.selectTagById(tagId);
    }
}





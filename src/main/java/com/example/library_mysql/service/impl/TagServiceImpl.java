package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.mapper.TagMapper;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.TagService;
import com.example.library_mysql.vo.TagListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
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
    public Tag selectTagByName(String name) {
        Tag tag = lambdaQuery().eq(Tag::getTagName, name).one();
        if (tag != null) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getTagId, tag.getTagId()).count();
            tag.setBookNumber(bookNumber);
        }
        return tag;
    }

    @Override
    public List<Tag> searchByName(String searchKey) {
        List<Tag> tagList = lambdaQuery().like(Tag::getTagName, searchKey).list();
        if (tagList.isEmpty()) {
            return null;
        }
        setBookNumber(tagList);
        return tagList;
    }

    @Override
    public R<TagListVo> getAllTagListVo() {
        List<Tag> tagList = lambdaQuery().orderByAsc(Tag::getTagId).list();
        if (tagList.isEmpty()) {
            return R.error("无标签数据");
        }
        setBookNumber(tagList);
        TagListVo tagListVo = new TagListVo(tagList);
        tagListVo.setPagesNumber(0L);
        return R.success(tagListVo);
    }

    @Override
    public R<TagListVo> getTagListVoByPage(int page) {
        List<Tag> tagList = lambdaQuery().orderByAsc(Tag::getTagId).page(new Page<>(page, 10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(Tag::getTagId).page(new Page<>(page, 10)).getPages();
        if (tagList.isEmpty()) {
            return R.error("无标签数据");
        }
        setBookNumber(tagList);
        TagListVo tagListVo = new TagListVo(tagList);
        tagListVo.setPagesNumber(pagesNumber);
        return R.success(tagListVo);
    }

    @Override
    public R<TagListVo> getTagListVo(int page, String sortItem, String sortType) {
        List<Tag> tagList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "tagId" -> lambdaQuery().orderByAsc(Tag::getTagId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> lambdaQuery().orderByAsc(Tag::getTagName).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Tag::getTagId).list();
            };
            case "desc" -> switch (sortItem) {
                case "tagId" -> lambdaQuery().orderByDesc(Tag::getTagId).page(new Page<>(page, 10)).getRecords();
                case "tagName" -> lambdaQuery().orderByDesc(Tag::getTagName).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Tag::getTagId).list();
            };
            default -> lambdaQuery().orderByAsc(Tag::getTagId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().orderByAsc(Tag::getTagId).page(new Page<>(page, 10)).getPages();

        if (tagList.isEmpty()) {
            return R.error("无标签数据");
        }
        setBookNumber(tagList);
        if (sortItem.equals("bookNumber"))
            if (sortType.equals("asc"))
                tagList = tagList.stream().sorted(Comparator.comparing(Tag::getBookNumber)).skip((page - 1) * 10L).limit(10).toList();
            else if (sortType.equals("desc"))
                tagList = tagList.stream().sorted(Comparator.comparing(Tag::getBookNumber).reversed()).skip((page - 1) * 10L).limit(10).toList();

        TagListVo tagListVo = new TagListVo(tagList);
        tagListVo.setPagesNumber(pagesNumber);
        return R.success(tagListVo);
    }

    @Override
    public R<Boolean> deleteTagById(int id, LocalDateTime updateTime) {
        Tag tag = lambdaQuery().eq(Tag::getTagId, id).one();
        if (tag == null) {
            return R.error("标签信息删除失败（不存在该标签）");
        } else {
            if (bookService.deleteBookByOtherId("tagId", id, updateTime)) {
                tag.setUpdateTime(updateTime);
                updateById(tag);
                if (removeById(id))
                    return R.success(true);
                else
                    return R.error("标签信息删除失败");
            } else {
                return R.error("关联信息删除失败（书籍）");
            }
        }
    }

    private void setBookNumber(List<Tag> tagList) {
        for (Tag tag : tagList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getTagId, tag.getTagId()).count();
            tag.setBookNumber(bookNumber);
        }
    }
}

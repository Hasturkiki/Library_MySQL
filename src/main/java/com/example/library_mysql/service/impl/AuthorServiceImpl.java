package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.JointAuthorTable;
import com.example.library_mysql.mapper.AuthorMapper;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.JointAuthorTableService;
import com.example.library_mysql.vo.AuthorListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【author(作者信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
        implements AuthorService {

    @Resource
    private BookService bookService;
    @Resource
    private JointAuthorTableService jointAuthorTableService;

    @Resource
    private AuthorMapper authorMapper;

    @Override
    public Author selectAuthorById(int id) {
        Author author = lambdaQuery().eq(Author::getAuthorId, id).one();
        long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, id).eq(Book::getJointAuthorTableId, 0).count();
        bookNumber += jointAuthorTableService.lambdaQuery().eq(JointAuthorTable::getAuthorId, id).count();
        if (author != null)
            author.setBookNumber(bookNumber);
        return author;
    }

    @Override
    public Author selectAuthorByName(String name) {
        Author author = lambdaQuery().eq(Author::getAuthorName, name).one();
        if (author != null) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, author.getAuthorId()).eq(Book::getJointAuthorTableId, 0).count();
            bookNumber += jointAuthorTableService.lambdaQuery().eq(JointAuthorTable::getAuthorId, author.getAuthorId()).count();
            author.setBookNumber(bookNumber);
        }
        return author;
    }

    @Override
    public List<Author> searchByName(String searchKey) {
        List<Author> authorList = lambdaQuery().like(Author::getAuthorName, searchKey).list();
        if (authorList.isEmpty()) {
            return null;
        }
        setBookNumber(authorList);
        return authorList;
    }

    @Override
    public R<AuthorListVo> getAllAuthorListVo() {
        List<Author> authorList = lambdaQuery().orderByAsc(Author::getAuthorId).list();
        if (authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        setBookNumber(authorList);
        AuthorListVo authorListVo = new AuthorListVo(authorList);
        authorListVo.setPagesNumber(0L);
        return R.success(authorListVo);
    }

    @Override
    public R<AuthorListVo> getAuthorListVoByPage(int page) {
        List<Author> authorList = lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page, 10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page, 10)).getPages();
        if (authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        setBookNumber(authorList);
        AuthorListVo authorListVo = new AuthorListVo(authorList);
        authorListVo.setPagesNumber(pagesNumber);
        return R.success(authorListVo);
    }

    @Override
    public R<AuthorListVo> getAuthorListVo(int page, String sortItem, String sortType) {
        List<Author> authorList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "authorId" -> lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> lambdaQuery().orderByAsc(Author::getAuthorName).page(new Page<>(page, 10)).getRecords();
                case "authorSex" -> lambdaQuery().orderByAsc(Author::getAuthorSex).page(new Page<>(page, 10)).getRecords();
                case "authorAge" -> lambdaQuery().orderByAsc(Author::getAuthorAge).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Author::getAuthorId).list();
            };
            case "desc" -> switch (sortItem) {
                case "authorId" -> lambdaQuery().orderByDesc(Author::getAuthorId).page(new Page<>(page, 10)).getRecords();
                case "authorName" -> lambdaQuery().orderByDesc(Author::getAuthorName).page(new Page<>(page, 10)).getRecords();
                case "authorSex" -> lambdaQuery().orderByDesc(Author::getAuthorSex).page(new Page<>(page, 10)).getRecords();
                case "authorAge" -> lambdaQuery().orderByDesc(Author::getAuthorAge).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Author::getAuthorId).list();
            };
            default -> lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().orderByAsc(Author::getAuthorId).page(new Page<>(page, 10)).getPages();

        if (authorList.isEmpty()) {
            return R.error("无作者数据");
        }
        setBookNumber(authorList);
        if (sortItem.equals("bookNumber"))
            if (sortType.equals("asc"))
                authorList = authorList.stream().sorted(Comparator.comparing(Author::getBookNumber)).skip((page - 1) * 10L).limit(10).toList();
            else if (sortType.equals("desc"))
                authorList = authorList.stream().sorted(Comparator.comparing(Author::getBookNumber).reversed()).skip((page - 1) * 10L).limit(10).toList();

        AuthorListVo authorListVo = new AuthorListVo(authorList);
        authorListVo.setPagesNumber(pagesNumber);
        return R.success(authorListVo);
    }

    // 删除失败恢复时可能会将非本次删除条目恢复，待修改
    @Override
    public R<Boolean> deleteAuthorById(int id, LocalDateTime updateTime) {
        Author author = lambdaQuery().eq(Author::getAuthorId, id).one();
        if (author == null) {
            return R.error("作者信息删除失败（不存在该作者）");
        } else {
            if (bookService.deleteBookByOtherId("authorId", id, updateTime)) {
                if (jointAuthorTableService.deleteJointAuthorTableByAuthorId(id, updateTime)) {
                    author.setUpdateTime(updateTime);
                    updateById(author);
                    if (removeById(id))
                        return R.success(true);
                    else
                        return R.error("作者信息删除失败");
                } else {
                    authorMapper.recoveryById(id);
                    bookService.recoveryByOtherId("authorId", id, updateTime);
                    return R.error("关联信息删除失败（共同作者表）");
                }
            } else {
                return R.error("关联信息删除失败（书籍）");
            }
        }
    }

    private void setBookNumber(List<Author> authorList) {
        for (Author author : authorList) {
            long bookNumber = bookService.lambdaQuery().eq(Book::getAuthorId, author.getAuthorId()).eq(Book::getJointAuthorTableId, 0).count();
            bookNumber += jointAuthorTableService.lambdaQuery().eq(JointAuthorTable::getAuthorId, author.getAuthorId()).count();
            author.setBookNumber(bookNumber);
        }
    }
}

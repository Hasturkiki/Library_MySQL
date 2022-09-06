package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.BookVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "书籍主页")
@Controller
@RequestMapping("/book")
public class BookController {
    @ApiIgnore
    @RequestMapping("/bookHome")
    public String home() {
        return "bookHome";
    }

    @Resource
    private AuthorService authorService;
    @Resource
    private BookService bookService;
    @Resource
    private PublishingCompanyService publishingCompanyService;
    @Resource
    private ReaderService readerService;
    @Resource
    private TagService tagService;

    @ResponseBody
    @GetMapping("/getAll")
    @ApiOperation("获取所有书籍的列表")
    public R<List<BookVo>> getAllTag() {
        return bookService.getBookVoList();
    }
}

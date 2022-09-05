package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.BookBorrowTable;
import com.example.library_mysql.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "借书表主页")
@Controller
@RequestMapping("/bookBorrowTable")
public class BookBorrowTableController {
    @ApiIgnore
    @RequestMapping("/bookBorrowTableHome")
    public String home() {
        return "bookBorrowTableHome";
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
    @Resource
    private BookBorrowTableService bookBorrowTableService;

    @ResponseBody
    @GetMapping("/getAll")
    @ApiOperation("获取所有借书表的列表")
    public R<List<BookBorrowTable>> getAllTag() {
        return bookBorrowTableService.getBookBorrowTableList();
    }
}

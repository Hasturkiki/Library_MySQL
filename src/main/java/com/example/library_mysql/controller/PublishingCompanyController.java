package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.PublishingCompany;
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

@Api(tags = "出版社主页")
@Controller
@RequestMapping("/publishingCompany")
public class PublishingCompanyController {
    @ApiIgnore
    @RequestMapping("/publishingCompanyHome")
    public String home() {
        return "publishingCompanyHome";
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
    @ApiOperation("获取所有出版社的列表")
    public R<List<PublishingCompany>> getAllTag() {
        return publishingCompanyService.getPublishingCompanyList();
    }
}

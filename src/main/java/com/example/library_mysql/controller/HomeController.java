package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.*;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Api(tags = "首页")
@Controller
public class HomeController {
    @ApiIgnore
    @RequestMapping({"/index", "/home", "/"})
    public String home() {
        return "index";
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
    @PostMapping("/search")
    @ApiOperation("首页搜索")
    @ApiImplicitParam(name = "searchValue", value = "搜索关键词", required = true, paramType = "query", dataType = "String")
    public R<Object> show(String searchValue) throws UnsupportedEncodingException {
        if (searchValue != null) {
            int id = parseInt(searchValue);
            List<Object> result = new ArrayList<>();
            Author author = authorService.selectAuthorById(id);
//            Book book = bookService.selectBookById(id);
            BookVo bookVo = bookService.selectBookVoById(id);
            PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(id);
            Reader reader = readerService.selectReaderById(id);
            Tag tag = tagService.selectTagById(id);

            result.add(author);
            result.add(bookVo);
            result.add(publishingCompany);
            result.add(reader);
            result.add(tag);

            String str = new String(result.toString().getBytes(), "GBK");
            System.out.println("searchValue " + searchValue + " success.\n" + str);
            return R.success(result);
        } else {
            System.out.println("search fail.");
            return R.error("search fail.");
        }
    }
}

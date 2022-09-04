package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.*;
import com.example.library_mysql.service.*;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Api(tags = "主页")
@Controller
public class HomeController {
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

    @RequestMapping("/search")
    @ResponseBody
    public R<Object> show(String searchValue) throws UnsupportedEncodingException {
        if (searchValue != null) {
            int id = parseInt(searchValue);
            List<Object> result = new ArrayList<>();
            Author author = authorService.selectAuthorById(id);
            Book book = bookService.selectBookById(id);
            PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(id);
            Reader reader = readerService.selectReaderById(id);
            Tag tag = tagService.selectTagById(id);

            result.add(author);
            result.add(book);
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

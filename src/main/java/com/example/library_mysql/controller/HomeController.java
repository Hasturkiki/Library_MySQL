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
import java.util.Objects;

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
    private BookBorrowTableService bookBorrowTableService;
    @Resource
    private BookService bookService;
    @Resource
    private JointAuthorTableService jointAuthorTableService;
    @Resource
    private PublishingCompanyService publishingCompanyService;
    @Resource
    private ReaderService readerService;
    @Resource
    private TagService tagService;

    @SuppressWarnings("rawtypes")
    @ResponseBody
    @PostMapping("/search")
    @ApiOperation("首页搜索")
    @ApiImplicitParam(name = "searchKey", value = "搜索关键词", required = true, paramType = "query", dataType = "String")
    public R<Object> show(String searchKey) throws UnsupportedEncodingException {
        if (searchKey != null) {
            List<List> result = new ArrayList<>();
            List<Author> authorList = authorService.searchByName(searchKey);
            List<BookVo> bookVoList = bookService.searchByName(searchKey);
            List<PublishingCompany> publishingCompanyList = publishingCompanyService.searchByName(searchKey);
            List<Reader> readerList = readerService.searchByName(searchKey);
            List<Tag> tagList = tagService.searchByName(searchKey);

            result.add(authorList);
            result.add(bookVoList);
            result.add(publishingCompanyList);
            result.add(readerList);
            result.add(tagList);

            //to_do 修复中文显示问题 （尚未完全解决，clean后会无法显示中文字符，需重设项目编码为UTF-8后编译一次再重设为GBK才能正确显示）
            System.out.println("\nsearch '" + new String(searchKey.getBytes(), "GBK") + "' success.");
            if (result.stream().anyMatch(Objects::nonNull)) {
                System.out.println("result: {");
                int i = 0;
                String[] strings = {"Author", "Book", "PublishingCompany", "Reader", "Tag"};
                for (List objectList : result) {
                    if (objectList == null)
                        System.out.println("\t" + strings[i] + ": null");
                    else {
                        System.out.println("\t" + strings[i] + ": ");
                        for (Object object : objectList) {
                            String str = object.toString().split("[(]", 2)[1]
                                    .replaceFirst(".$", "").replace("=", ": ");
                            if (str.split("[(]").length == 2)
                                str = str.split("[(]")[1].replace(")", "");
                            System.out.println("\t\t[" + str + "]");
                        }
                    }
                    i++;
                }
                System.out.println("}");
            } else
                System.out.println("search_result: null");
            return R.success(result);
        } else {
            System.out.println("search fail.");
            return R.error("search fail.");
        }
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @PostMapping("/showOne")
    @ApiOperation("单个展示测试")
    @ApiImplicitParam(name = "key", value = "索引ID", required = true, paramType = "query", dataType = "int")
    public R<Object> showOne(int key) {
        List<Object> result = new ArrayList<>();
        Author author = authorService.selectAuthorById(key);
        BookBorrowTableVo bookBorrowTableVo = bookBorrowTableService.selectBookBorrowTableVoById(key);
        BookVo bookVo = bookService.selectBookVoById(key);
        JointAuthorTableVo jointAuthorTableVo = jointAuthorTableService.selectJointAuthorTableVoById(key);
        PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(key);
        Reader reader = readerService.selectReaderById(key);
        Tag tag = tagService.selectTagById(key);

        result.add(author);
        result.add(bookBorrowTableVo);
        result.add(bookVo);
        result.add(jointAuthorTableVo);
        result.add(publishingCompany);
        result.add(reader);
        result.add(tag);

        System.out.println("\nshow '" + key + "' success.");
        if (result.stream().anyMatch(Objects::nonNull)) {
            System.out.println("show_result: {");
            int i = 0;
            String[] strings = {"Author", "BookBorrowTable", "Book", "JointAuthorTable", "PublishingCompany", "Reader", "Tag"};
            for (Object object : result) {
                if (object == null)
                    System.out.println("\t" + strings[i] + ": null");
                else {
                    System.out.println("\t" + strings[i] + ": ");
                    String str = object.toString().split("[(]", 2)[1]
                            .replaceFirst(".$", "").replace("=", ": ");
                    if (str.split("[(]").length == 2)
                        str = str.split("[(]")[1].replace(")", "");
                    System.out.println("\t\t[" + str + "]");
                }
                i++;
            }

            System.out.println("}");
            return R.success(result);
        } else {
            System.out.println("result: null");
            return R.error("无对应信息");
        }
    }
}

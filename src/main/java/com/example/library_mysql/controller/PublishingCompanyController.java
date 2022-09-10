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
    @PostMapping("/getPublishingCompanyListVoByPage")
    @ApiOperation("分页获取出版社列表扩展：page=0时代表获取所有出版社")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<PublishingCompanyListVo> getPublishingCompanyListVoByPage(int page) {
        if (page == 0)
            return publishingCompanyService.getAllPublishingCompanyListVo();
        else
            return publishingCompanyService.getPublishingCompanyListVoByPage(page);
    }
}

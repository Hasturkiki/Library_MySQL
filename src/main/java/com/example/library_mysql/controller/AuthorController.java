package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "作者主页")
@Controller
@RequestMapping("/author")
public class AuthorController {
    @ApiIgnore
    @RequestMapping("/authorHome")
    public String home() {
        return "authorHome";
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
    @PostMapping("/getAuthorListVoByPage")
    @ApiOperation("分页获取作者列表扩展：page=0时代表获取所有作者")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<AuthorListVo> getAuthorListVoByPage(int page) {
        if (page == 0)
            return authorService.getAllAuthorListVo();
        else
            return authorService.getAuthorListVoByPage(page);
    }
}

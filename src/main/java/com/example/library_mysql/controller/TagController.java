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

@Api(tags = "标签主页")
@Controller
@RequestMapping("/tag")
public class TagController {
    @ApiIgnore
    @RequestMapping("/tagHome")
    public String home() {
        return "tagHome";
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
    @PostMapping("/getTagListVoByPage")
    @ApiOperation("分页获取标签列表扩展：page=0时代表获取所有标签")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<TagListVo> getTagListVoByPage(int page) {
        if (page == 0)
            return tagService.getAllTagListVo();
        else
            return tagService.getTagListVoByPage(page);
    }
}

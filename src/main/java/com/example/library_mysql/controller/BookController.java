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
    @PostMapping("/getBookVoListVoByPage")
    @ApiOperation("分页获取书籍扩展列表扩展：page=0时代表获取所有书籍扩展")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<BookVoListVo> getBookVoListVoByPage(int page) {
        if (page == 0)
            return bookService.getAllBookVoListVo();
        else
            return bookService.getBookVoListVoByPage(page);
    }
}

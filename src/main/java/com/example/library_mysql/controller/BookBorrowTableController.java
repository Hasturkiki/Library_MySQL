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
    @PostMapping("/getBookBorrowTableVoListVoByPage")
    @ApiOperation("分页获取借书表扩展列表扩展：page=0时代表获取所有借书表扩展")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<BookBorrowTableVoListVo> getBookBorrowTableVoListVoByPage(int page) {
        if (page == 0)
            return bookBorrowTableService.getAllBookBorrowTableVoListVo();
        else
            return bookBorrowTableService.getBookBorrowTableVoListVoByPage(page);
    }
}

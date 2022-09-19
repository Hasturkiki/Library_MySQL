package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @PostMapping("/getBookBorrowTableVoListVo")
    @ApiOperation("依据排序分页获取借书表扩展列表扩展：page=0时代表获取所有借书表扩展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<BookBorrowTableVoListVo> getBookBorrowTableVoListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return bookBorrowTableService.getAllBookBorrowTableVoListVo();
        else
            return bookBorrowTableService.getBookBorrowTableVoListVo(page, sortItem, sortType);
    }
}

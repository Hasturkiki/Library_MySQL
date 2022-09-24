package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
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
    private BookService bookService;

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

    @ResponseBody
    @PostMapping("/getBookVoListVo")
    @ApiOperation("依据排序分页获取书籍扩展列表扩展：page=0时代表获取所有书籍扩展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<BookVoListVo> getBookVoListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return bookService.getAllBookVoListVo();
        else
            return bookService.getBookVoListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @PostMapping("/showOne")
    @ApiOperation("书籍扩展信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<BookVo> showOne(String key) {
        BookVo bookVo;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            bookVo = bookService.selectBookVoById(Integer.parseInt(key));
        else
            bookVo = bookService.selectBookVoByName(key);

        if (bookVo == null)
            return R.error("无对应书籍扩展信息");
        return R.success(bookVo);
    }
}

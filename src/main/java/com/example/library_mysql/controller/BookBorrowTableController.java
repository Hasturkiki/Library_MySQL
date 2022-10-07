package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.BookBorrowTable;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
    @GetMapping("/getBookBorrowTableVoListVoByPage")
    @ApiOperation("分页获取借书表扩展列表扩展：page=0时代表获取所有借书表扩展")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<BookBorrowTableVoListVo> getBookBorrowTableVoListVoByPage(int page) {
        if (page == 0)
            return bookBorrowTableService.getAllBookBorrowTableVoListVo();
        else
            return bookBorrowTableService.getBookBorrowTableVoListVoByPage(page);
    }

    @ResponseBody
    @GetMapping("/getBookBorrowTableVoListVo")
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

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @GetMapping("/showOne")
    @ApiOperation("借书表扩展信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<BookBorrowTableVo> showOne(String key) {
        BookBorrowTableVo bookBorrowTableVo;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            bookBorrowTableVo = bookBorrowTableService.selectBookBorrowTableVoById(Integer.parseInt(key));
        else
            bookBorrowTableVo = null;

        if (bookBorrowTableVo == null)
            return R.error("无对应借书表扩展信息");
        return R.success(bookBorrowTableVo);
    }

    @ApiIgnore
    @RequestMapping("/selectByBook")
    public String showList() {
        return "bookBorrowTable_showList";
    }

    @ResponseBody
    @GetMapping("/selectByBookKey")
    @ApiOperation("依据排序分页获取对应id书籍下借书表扩展列表扩展：page=0时代表获取所有借书表扩展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "书籍ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<BookBorrowTableVoListVo> selectByAuthorKey(int key, int page, String sortItem, String sortType) {
        if (page == 0)
            return bookBorrowTableService.BookBorrowTablesByBook(key);
        else
            return bookBorrowTableService.BookBorrowTablesByBookWithCondition(key, page, sortItem, sortType);
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation("借书表信息更新")
    public R<BookBorrowTableVo> updateBookBorrowTable(@RequestBody BookBorrowTable bookBorrowTable) {
        bookBorrowTable.setUpdateTime(LocalDateTime.now());
        if (bookBorrowTableService.updateById(bookBorrowTable)) {
            return R.success(bookBorrowTableService.selectBookBorrowTableVoById(bookBorrowTable.getBooksBorrowTableId()));
        } else {
            return R.error("借书表信息更新失败");
        }
    }

    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation("借书表信息删除")
    @ApiImplicitParam(name = "id", value = "待删除借书表ID", required = true)
    public R<Boolean> deleteBookBorrowTable(int id) {
        return bookBorrowTableService.deleteBookBorrowTableById(id, LocalDateTime.now());
    }
}

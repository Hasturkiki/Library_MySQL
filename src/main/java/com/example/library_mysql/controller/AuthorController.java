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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @ResponseBody
    @GetMapping("/getAuthorListVoByPage")
    @ApiOperation("分页获取作者列表扩展：page=0时代表获取所有作者")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<AuthorListVo> getAuthorListVoByPage(int page) {
        if (page == 0)
            return authorService.getAllAuthorListVo();
        else
            return authorService.getAuthorListVoByPage(page);
    }

    @ResponseBody
    @GetMapping("/getAuthorListVo")
    @ApiOperation("依据排序分页获取作者列表扩展：page=0时代表获取所有作者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<AuthorListVo> getAuthorListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return authorService.getAllAuthorListVo();
        else
            return authorService.getAuthorListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @GetMapping("/showOne")
    @ApiOperation("作者信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<Author> showOne(String key) {
        Author author;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            author = authorService.selectAuthorById(Integer.parseInt(key));
        else
            author = authorService.selectAuthorByName(key);

        if (author == null)
            return R.error("无对应作者信息");
        return R.success(author);
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation("作者信息更新")
    public R<Author> updateAuthor(@RequestBody Author author) {
        author.setUpdateTime(LocalDateTime.now());
        if (authorService.updateById(author)) {
            return R.success(authorService.selectAuthorById(author.getAuthorId()));
        } else {
            return R.error("作者信息更新失败");
        }
    }

    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation("作者信息删除")
    @ApiImplicitParam(name = "id", value = "待删除作者ID", required = true)
    public R<Boolean> deleteAuthor(int id) {
        return authorService.deleteAuthorById(id, LocalDateTime.now());
    }
}

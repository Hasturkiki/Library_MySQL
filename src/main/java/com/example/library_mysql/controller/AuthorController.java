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
    @PostMapping("/getAuthorListVoByPage")
    @ApiOperation("分页获取作者列表扩展：page=0时代表获取所有作者")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<AuthorListVo> getAuthorListVoByPage(int page) {
        if (page == 0)
            return authorService.getAllAuthorListVo();
        else
            return authorService.getAuthorListVoByPage(page);
    }

    @ResponseBody
    @PostMapping("/getAuthorListVo")
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
}

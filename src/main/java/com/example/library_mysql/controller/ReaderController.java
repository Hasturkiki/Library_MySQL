package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.Reader;
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

@Api(tags = "读者主页")
@Controller
@RequestMapping("/reader")
public class ReaderController {
    @ApiIgnore
    @RequestMapping("/readerHome")
    public String home() {
        return "readerHome";
    }

    @Resource
    private ReaderService readerService;

    @ResponseBody
    @PostMapping("/getReaderListVoByPage")
    @ApiOperation("分页获取读者列表扩展：page=0时代表获取所有读者")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<ReaderListVo> getReaderListVoByPage(int page) {
        if (page == 0)
            return readerService.getAllReaderListVo();
        else
            return readerService.getReaderListVoByPage(page);
    }

    @ResponseBody
    @PostMapping("/getReaderListVo")
    @ApiOperation("依据排序分页获取读者列表扩展：page=0时代表获取所有读者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<ReaderListVo> getReaderListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return readerService.getAllReaderListVo();
        else
            return readerService.getReaderListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @PostMapping("/showOne")
    @ApiOperation("读者信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<Reader> showOne(String key) {
        Reader reader;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            reader = readerService.selectReaderById(Integer.parseInt(key));
        else
            reader = readerService.selectReaderByName(key);

        if (reader == null)
            return R.error("无对应读者信息");
        return R.success(reader);
    }
}

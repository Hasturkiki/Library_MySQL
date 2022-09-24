package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Author;
import com.example.library_mysql.domain.PublishingCompany;
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

@Api(tags = "出版社主页")
@Controller
@RequestMapping("/publishingCompany")
public class PublishingCompanyController {
    @ApiIgnore
    @RequestMapping("/publishingCompanyHome")
    public String home() {
        return "publishingCompanyHome";
    }

    @Resource
    private PublishingCompanyService publishingCompanyService;

    @ResponseBody
    @PostMapping("/getPublishingCompanyListVoByPage")
    @ApiOperation("分页获取出版社列表扩展：page=0时代表获取所有出版社")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<PublishingCompanyListVo> getPublishingCompanyListVoByPage(int page) {
        if (page == 0)
            return publishingCompanyService.getAllPublishingCompanyListVo();
        else
            return publishingCompanyService.getPublishingCompanyListVoByPage(page);
    }

    @ResponseBody
    @PostMapping("/getPublishingCompanyListVo")
    @ApiOperation("依据排序分页获取出版社列表扩展：page=0时代表获取所有出版社")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<PublishingCompanyListVo> getPublishingCompanyListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return publishingCompanyService.getAllPublishingCompanyListVo();
        else
            return publishingCompanyService.getPublishingCompanyListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @PostMapping("/showOne")
    @ApiOperation("出版社信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<PublishingCompany> showOne(String key) {
        PublishingCompany publishingCompany;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            publishingCompany = publishingCompanyService.selectPublishingCompanyById(Integer.parseInt(key));
        else
            publishingCompany = publishingCompanyService.selectPublishingCompanyByName(key);

        if (publishingCompany == null)
            return R.error("无对应出版社信息");
        return R.success(publishingCompany);
    }
}

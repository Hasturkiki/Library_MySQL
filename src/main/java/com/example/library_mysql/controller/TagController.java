package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Tag;
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
    private TagService tagService;

    @ResponseBody
    @GetMapping("/getTagListVoByPage")
    @ApiOperation("分页获取标签列表扩展：page=0时代表获取所有标签")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<TagListVo> getTagListVoByPage(int page) {
        if (page == 0)
            return tagService.getAllTagListVo();
        else
            return tagService.getTagListVoByPage(page);
    }

    @ResponseBody
    @GetMapping("/getTagListVo")
    @ApiOperation("依据排序分页获取标签列表扩展：page=0时代表获取所有标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<TagListVo> getTagListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return tagService.getAllTagListVo();
        else
            return tagService.getTagListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @GetMapping("/showOne")
    @ApiOperation("标签信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<Tag> showOne(String key) {
        Tag tag;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            tag = tagService.selectTagById(Integer.parseInt(key));
        else
            tag = tagService.selectTagByName(key);

        if (tag == null)
            return R.error("无对应标签信息");
        return R.success(tag);
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation("标签信息更新")
    public R<Tag> updateTag(@RequestBody Tag tag) {
        tag.setUpdateTime(LocalDateTime.now());
        if (tagService.updateById(tag)) {
            return R.success(tagService.selectTagById(tag.getTagId()));
        } else {
            return R.error("标签信息更新失败");
        }
    }

    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation("标签信息删除")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    public R<Boolean> deleteTag(int id) {
        return tagService.deleteTagById(id, LocalDateTime.now());
    }
}

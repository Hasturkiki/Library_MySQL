package com.example.library_mysql.controller;

import com.example.library_mysql.common.R;
import com.example.library_mysql.service.*;
import com.example.library_mysql.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "共同作者表主页")
@Controller
@RequestMapping({"/jointAuthorTable", "/jointAuthorTableVo"})
public class JointAuthorTableController {
    @ApiIgnore
    @RequestMapping("/jointAuthorTableHome")
    public String home() {
        return "jointAuthorTableHome";
    }

    @Resource
    private JointAuthorTableService jointAuthorTableService;

    @ResponseBody
    @GetMapping("/getJointAuthorTableVoListVoByPage")
    @ApiOperation("分页获取共同作者表扩展列表扩展：page=0时代表获取所有共同作者表扩展")
    @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int")
    public R<JointAuthorTableVoListVo> getJointAuthorTableVoListVoByPage(int page) {
        if (page == 0)
            return jointAuthorTableService.getAllJointAuthorTableVoListVo();
        else
            return jointAuthorTableService.getJointAuthorTableVoListVoByPage(page);
    }

    @ResponseBody
    @GetMapping("/getJointAuthorTableVoListVo")
    @ApiOperation("依据排序分页获取共同作者表扩展列表扩展：page=0时代表获取所有共同作者表扩展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sortItem", value = "排序项", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式", required = false, paramType = "query", dataType = "String")
    })
    public R<JointAuthorTableVoListVo> getJointAuthorTableVoListVo(int page, String sortItem, String sortType) {
        if (page == 0)
            return jointAuthorTableService.getAllJointAuthorTableVoListVo();
        else
            return jointAuthorTableService.getJointAuthorTableVoListVo(page, sortItem, sortType);
    }

    @ApiIgnore
    @RequestMapping("/getOne")
    public String getOne() {
        return "showOne";
    }

    @ResponseBody
    @GetMapping("/showOne")
    @ApiOperation("共同作者表扩展信息展示")
    @ApiImplicitParam(name = "key", value = "索引key", required = true, paramType = "query", dataType = "String")
    public R<JointAuthorTableVo> showOne(String key) {
        JointAuthorTableVo jointAuthorTableVo;
        if (key != null && key.chars().anyMatch(Character::isDigit))
            jointAuthorTableVo = jointAuthorTableService.selectJointAuthorTableVoById(Integer.parseInt(key));
        else
            jointAuthorTableVo = null;

        if (jointAuthorTableVo == null)
            return R.error("无对应共同作者表扩展信息");
        return R.success(jointAuthorTableVo);
    }
}

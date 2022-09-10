package com.example.library_mysql.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "JointAuthorTableVoListVo对象", description = "共同作者表扩展列表扩展")
public class JointAuthorTableVoListVo {
    @ApiModelProperty("共同作者表扩展列表原型")
    private List<JointAuthorTableVo> jointAuthorTableVoList;

    @ApiModelProperty("共同作者表扩展列表页数：pagesNumber=0时代表获取全部共同作者表扩展的列表，pagesNumber=1时代表共同作者表扩展条目数少于单页容量10条，已获取全部共同作者表扩展列表")
    private Long pagesNumber;

    public JointAuthorTableVoListVo(List<JointAuthorTableVo> jointAuthorTableVoList) {
        this.jointAuthorTableVoList = jointAuthorTableVoList;
    }
}

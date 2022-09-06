package com.example.library_mysql.vo;

import com.example.library_mysql.domain.JointAuthorTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "JointAuthorTable对象", description = "共同作者表扩展")
public class JointAuthorTableVo {

    @ApiModelProperty("共同作者表原型")
    private JointAuthorTable jointAuthorTable;

    @ApiModelProperty("该共同作者表关联书籍名")
    private String bookName;

    @ApiModelProperty("该共同作者表关联作者名")
    private String authorNames;

    public JointAuthorTableVo(JointAuthorTable jointAuthorTable) {
        this.jointAuthorTable = jointAuthorTable;
    }
}

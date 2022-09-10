package com.example.library_mysql.vo;

import com.example.library_mysql.domain.PublishingCompany;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "PublishingCompanyListVo对象", description = "出版社列表扩展")
public class PublishingCompanyListVo {
    @ApiModelProperty("出版社列表原型")
    private List<PublishingCompany> PublishingCompanyList;

    @ApiModelProperty("出版社列表页数：pagesNumber=0时代表获取全部出版社的列表，pagesNumber=1时代表出版社条目数少于单页容量10条，已获取全部出版社列表")
    private Long pagesNumber;

    public PublishingCompanyListVo(List<PublishingCompany> PublishingCompanyList) {
        this.PublishingCompanyList = PublishingCompanyList;
    }
}

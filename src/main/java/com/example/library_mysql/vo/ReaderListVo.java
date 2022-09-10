package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Reader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "ReaderListVo对象", description = "读者列表扩展")
public class ReaderListVo {
    @ApiModelProperty("读者列表原型")
    private List<Reader> readerList;

    @ApiModelProperty("读者列表页数：pagesNumber=0时代表获取全部读者的列表，pagesNumber=1时代表读者条目数少于单页容量10条，已获取全部读者列表")
    private Long pagesNumber;

    public ReaderListVo(List<Reader> readerList) {
        this.readerList = readerList;
    }
}

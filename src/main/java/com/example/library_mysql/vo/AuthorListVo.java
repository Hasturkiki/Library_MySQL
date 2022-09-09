package com.example.library_mysql.vo;

import com.example.library_mysql.domain.Author;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "AuthorListVo����", description = "�����б���չ")
public class AuthorListVo {
    @ApiModelProperty("�����б�ԭ��")
    private List<Author> authorList;

    @ApiModelProperty("�����б��ҳҳ����ҳ��Ϊ0ʱ�����ȡ��������")
    private Long pagesNumber;

    public AuthorListVo(List<Author> authorList) {
        this.authorList = authorList;
    }
}

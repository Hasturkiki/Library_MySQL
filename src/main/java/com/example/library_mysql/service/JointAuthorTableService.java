package com.example.library_mysql.service;

import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.JointAuthorTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.library_mysql.vo.JointAuthorTableVoListVo;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【joint_author_table(共同作者表信息)】的数据库操作Service
* @createDate 2022-09-04 16:34:36
*/
public interface JointAuthorTableService extends IService<JointAuthorTable> {

    R<List<JointAuthorTable>> getJointAuthorTableList();

    R<JointAuthorTableVoListVo> getAllJointAuthorTableVoListVo();

    R<JointAuthorTableVoListVo> getJointAuthorTableVoListVoByPage(int page);
}

package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.JointAuthorTable;
import com.example.library_mysql.service.JointAuthorTableService;
import com.example.library_mysql.mapper.JointAuthorTableMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Hastur kiki
* @description 针对表【joint_author_table(共同作者表信息)】的数据库操作Service实现
* @createDate 2022-09-04 16:34:36
*/
@Service
public class JointAuthorTableServiceImpl extends ServiceImpl<JointAuthorTableMapper, JointAuthorTable>
    implements JointAuthorTableService{

    @Override
    public R<List<JointAuthorTable>> getJointAuthorTableList() {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if(jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        return R.success(jointAuthorTableList);
    }
}





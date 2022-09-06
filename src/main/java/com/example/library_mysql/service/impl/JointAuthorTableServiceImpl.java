package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Book;
import com.example.library_mysql.domain.JointAuthorTable;
import com.example.library_mysql.service.AuthorService;
import com.example.library_mysql.service.BookService;
import com.example.library_mysql.service.JointAuthorTableService;
import com.example.library_mysql.mapper.JointAuthorTableMapper;
import com.example.library_mysql.vo.JointAuthorTableVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hastur kiki
 * @description 针对表【joint_author_table(共同作者表信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class JointAuthorTableServiceImpl extends ServiceImpl<JointAuthorTableMapper, JointAuthorTable>
        implements JointAuthorTableService {

    @Resource
    private BookService bookService;

    @Resource
    private AuthorService authorService;

    @Override
    public R<List<JointAuthorTable>> getJointAuthorTableList() {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        return R.success(jointAuthorTableList);
    }

    @Override
    public R<List<JointAuthorTableVo>> getJointAuthorTableVoList() {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        List<JointAuthorTableVo> jointAuthorTableVoList = new ArrayList<>();
        for (JointAuthorTable jointAuthorTable : jointAuthorTableList) {
            int tableId = jointAuthorTable.getTableId();
            List<JointAuthorTable> jointAuthorTableListExist = jointAuthorTableVoList.stream().map(JointAuthorTableVo::getJointAuthorTable).toList();
            if (jointAuthorTableListExist.stream().map(JointAuthorTable::getTableId).toList().contains(tableId)) {
                JointAuthorTableVo jointAuthorTableVo = jointAuthorTableVoList.get(jointAuthorTableListExist.stream().map(JointAuthorTable::getTableId).toList().indexOf(tableId));
                jointAuthorTableVo.setAuthorNames(jointAuthorTableVo.getAuthorNames()+';'+authorService.selectAuthorById(jointAuthorTable.getAuthorId()).getAuthorName());
            } else {
                JointAuthorTableVo jointAuthorTableVo = new JointAuthorTableVo(jointAuthorTable);
                jointAuthorTableVo.setBookName(bookService.lambdaQuery().eq(Book::getJointAuthorTableId, tableId).one().getBookName());
                jointAuthorTableVo.setAuthorNames(authorService.selectAuthorById(jointAuthorTable.getAuthorId()).getAuthorName());
                jointAuthorTableVoList.add(jointAuthorTableVo);
            }
        }
        return R.success(jointAuthorTableVoList);
    }
}

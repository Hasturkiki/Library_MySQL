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
import com.example.library_mysql.vo.JointAuthorTableVoListVo;
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
    public JointAuthorTableVo selectJointAuthorTableVoById(int id) {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        JointAuthorTableVoListVo jointAuthorTableVoListVo = setJointAuthorTableVoListVo(jointAuthorTableList);
        if (id < 1 || id > jointAuthorTableVoListVo.getJointAuthorTableVoList().size())
            return null;
        return jointAuthorTableVoListVo.getJointAuthorTableVoList().get(id - 1);
    }

    @Override
    public R<List<JointAuthorTable>> getJointAuthorTableList() {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        return R.success(jointAuthorTableList);
    }

    @Override
    public R<JointAuthorTableVoListVo> getAllJointAuthorTableVoListVo() {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        JointAuthorTableVoListVo jointAuthorTableVoListVo = setJointAuthorTableVoListVo(jointAuthorTableList);
        jointAuthorTableVoListVo.setPagesNumber(0L);
        return R.success(jointAuthorTableVoListVo);
    }

    @Override
    public R<JointAuthorTableVoListVo> getJointAuthorTableVoListVoByPage(int page) {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        JointAuthorTableVoListVo jointAuthorTableVoListVo = setJointAuthorTableVoListVo(jointAuthorTableList);
        Long pagesNumber = (long) jointAuthorTableVoListVo.getJointAuthorTableVoList().size() / 10;
        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
            int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getJointAuthorTableId();
            int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getJointAuthorTableId();
            return jointAuthorTableId1 - jointAuthorTableId2;
        }).skip((page - 1) * 10L).limit(10).collect(Collectors.toList()));
        jointAuthorTableVoListVo.setPagesNumber(pagesNumber);
        return R.success(jointAuthorTableVoListVo);
    }

    @Override
    public R<JointAuthorTableVoListVo> getJointAuthorTableVoListVo(int page, String sortItem, String sortType) {
        List<JointAuthorTable> jointAuthorTableList = lambdaQuery().orderByAsc(JointAuthorTable::getJointAuthorTableId).list();
        if (jointAuthorTableList.isEmpty()) {
            return R.error("无共同作者表数据");
        }
        JointAuthorTableVoListVo jointAuthorTableVoListVo = setJointAuthorTableVoListVo(jointAuthorTableList);
        Long pagesNumber = (long) jointAuthorTableVoListVo.getJointAuthorTableVoList().size() / 10;
        switch (sortType) {
            case "asc":
                switch (sortItem) {
                    case "jointAuthorTableId":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getTableId();
                            int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getTableId();
                            return jointAuthorTableId1 - jointAuthorTableId2;
                        }).skip((page - 1) * 10L).limit(10).toList());
                    case "bookName":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = bookService.lambdaQuery().eq(Book::getBookName, jointAuthorTable1.getBookName()).one().getBookId();
                            int jointAuthorTableId2 = bookService.lambdaQuery().eq(Book::getBookName, jointAuthorTable2.getBookName()).one().getBookId();
                            return jointAuthorTableId1 - jointAuthorTableId2;
                        }).skip((page - 1) * 10L).limit(10).toList());
                    case "authorNames":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getAuthorId();
                            int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getAuthorId();
                            return jointAuthorTableId1 - jointAuthorTableId2;
                        }).skip((page - 1) * 10L).limit(10).toList());
                }
                break;
            case "desc":
                switch (sortItem) {
                    case "jointAuthorTableId":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getTableId();
                            int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getTableId();
                            return jointAuthorTableId2 - jointAuthorTableId1;
                        }).skip((page - 1) * 10L).limit(10).toList());
                    case "bookName":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = bookService.lambdaQuery().eq(Book::getBookName, jointAuthorTable1.getBookName()).one().getBookId();
                            int jointAuthorTableId2 = bookService.lambdaQuery().eq(Book::getBookName, jointAuthorTable2.getBookName()).one().getBookId();
                            return jointAuthorTableId2 - jointAuthorTableId1;
                        }).skip((page - 1) * 10L).limit(10).toList());
                    case "authorNames":
                        jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                            int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getAuthorId();
                            int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getAuthorId();
                            return jointAuthorTableId2 - jointAuthorTableId1;
                        }).skip((page - 1) * 10L).limit(10).toList());
                }
                break;
            default:
                jointAuthorTableVoListVo.setJointAuthorTableVoList(jointAuthorTableVoListVo.getJointAuthorTableVoList().stream().sorted((jointAuthorTable1, jointAuthorTable2) -> {
                    int jointAuthorTableId1 = jointAuthorTable1.getJointAuthorTable().getJointAuthorTableId();
                    int jointAuthorTableId2 = jointAuthorTable2.getJointAuthorTable().getJointAuthorTableId();
                    return jointAuthorTableId1 - jointAuthorTableId2;
                }).skip((page - 1) * 10L).limit(10).toList());
        }
        jointAuthorTableVoListVo.setPagesNumber(pagesNumber);
        return R.success(jointAuthorTableVoListVo);
    }

    private JointAuthorTableVoListVo setJointAuthorTableVoListVo(List<JointAuthorTable> jointAuthorTableList) {
        List<JointAuthorTableVo> jointAuthorTableVoList = new ArrayList<>();
        for (JointAuthorTable jointAuthorTable : jointAuthorTableList) {
            int tableId = jointAuthorTable.getTableId();
            List<JointAuthorTable> jointAuthorTableListExist = jointAuthorTableVoList.stream().map(JointAuthorTableVo::getJointAuthorTable).toList();
            if (jointAuthorTableListExist.stream().map(JointAuthorTable::getTableId).toList().contains(tableId)) {
                JointAuthorTableVo jointAuthorTableVo = jointAuthorTableVoList.get(jointAuthorTableListExist.stream().map(JointAuthorTable::getTableId).toList().indexOf(tableId));
                jointAuthorTableVo.setAuthorNames(jointAuthorTableVo.getAuthorNames() + ';' + authorService.selectAuthorById(jointAuthorTable.getAuthorId()).getAuthorName());
            } else {
                JointAuthorTableVo jointAuthorTableVo = new JointAuthorTableVo(jointAuthorTable);
                jointAuthorTableVo.setBookName(bookService.lambdaQuery().eq(Book::getJointAuthorTableId, tableId).one().getBookName());
                jointAuthorTableVo.setAuthorNames(authorService.selectAuthorById(jointAuthorTable.getAuthorId()).getAuthorName());
                jointAuthorTableVoList.add(jointAuthorTableVo);
            }
        }
        return new JointAuthorTableVoListVo(jointAuthorTableVoList);
    }
}

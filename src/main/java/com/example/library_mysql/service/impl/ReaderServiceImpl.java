package com.example.library_mysql.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library_mysql.common.R;
import com.example.library_mysql.domain.Reader;
import com.example.library_mysql.mapper.ReaderMapper;
import com.example.library_mysql.service.ReaderService;
import com.example.library_mysql.vo.ReaderListVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hastur kiki
 * @description 针对表【reader(读者信息)】的数据库操作Service实现
 * @createDate 2022-09-04 16:34:36
 */
@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader>
        implements ReaderService {

    @Override
    public Reader selectReaderById(int id) {
        return lambdaQuery().eq(Reader::getReaderId, id).one();
    }

    @Override
    public List<Reader> searchByName(String searchKey) {
        List<Reader> readerList = lambdaQuery().like(Reader::getReaderName, searchKey).list();
        if (readerList.isEmpty()) {
            return null;
        }
        return readerList;
    }

    @Override
    public R<ReaderListVo> getAllReaderListVo() {
        List<Reader> readerList = lambdaQuery().orderByAsc(Reader::getReaderId).list();
        if (readerList.isEmpty()) {
            return R.error("无读者数据");
        }
        ReaderListVo readerListVo = new ReaderListVo(readerList);
        readerListVo.setPagesNumber(0L);
        return R.success(readerListVo);
    }

    @Override
    public R<ReaderListVo> getReaderListVoByPage(int page) {
        List<Reader> readerList = lambdaQuery().orderByAsc(Reader::getReaderId).page(new Page<>(page, 10)).getRecords();
        long pagesNumber = lambdaQuery().orderByAsc(Reader::getReaderId).page(new Page<>(page, 10)).getPages();
        if (readerList.isEmpty()) {
            return R.error("无读者数据");
        }
        ReaderListVo readerListVo = new ReaderListVo(readerList);
        readerListVo.setPagesNumber(pagesNumber);
        return R.success(readerListVo);
    }

    @Override
    public R<ReaderListVo> getReaderListVo(int page, String sortItem, String sortType) {
        List<Reader> readerList = switch (sortType) {
            case "asc" -> switch (sortItem) {
                case "readerId" -> lambdaQuery().orderByAsc(Reader::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().orderByAsc(Reader::getReaderName).page(new Page<>(page, 10)).getRecords();
                case "readerSex" -> lambdaQuery().orderByAsc(Reader::getReaderSex).page(new Page<>(page, 10)).getRecords();
                case "readerAge" -> lambdaQuery().orderByAsc(Reader::getReaderAge).page(new Page<>(page, 10)).getRecords();
                case "saving" -> lambdaQuery().orderByAsc(Reader::getSaving).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Reader::getReaderId).list();
            };
            case "desc" -> switch (sortItem) {
                case "readerId" -> lambdaQuery().orderByDesc(Reader::getReaderId).page(new Page<>(page, 10)).getRecords();
                case "readerName" -> lambdaQuery().orderByDesc(Reader::getReaderName).page(new Page<>(page, 10)).getRecords();
                case "readerSex" -> lambdaQuery().orderByDesc(Reader::getReaderSex).page(new Page<>(page, 10)).getRecords();
                case "readerAge" -> lambdaQuery().orderByDesc(Reader::getReaderAge).page(new Page<>(page, 10)).getRecords();
                case "saving" -> lambdaQuery().orderByDesc(Reader::getSaving).page(new Page<>(page, 10)).getRecords();
                default -> lambdaQuery().orderByAsc(Reader::getReaderId).list();
            };
            default -> lambdaQuery().orderByAsc(Reader::getReaderId).page(new Page<>(page, 10)).getRecords();
        };
        long pagesNumber = lambdaQuery().orderByAsc(Reader::getReaderId).page(new Page<>(page, 10)).getPages();

        if (readerList.isEmpty()) {
            return R.error("无读者数据");
        }
        ReaderListVo readerListVo = new ReaderListVo(readerList);
        readerListVo.setPagesNumber(pagesNumber);
        return R.success(readerListVo);
    }
}





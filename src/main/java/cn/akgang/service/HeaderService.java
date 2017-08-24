package cn.akgang.service;

import cn.akgang.dao.DictionaryMapper;
import cn.akgang.dao.HeaderMapper;
import cn.akgang.entity.HeaderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akgang on 2017/8/14.
 */
@Service
public class HeaderService {


    @Autowired
    private HeaderMapper headerMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;


    public String getHeader(Long sourceId) {
        return dictionaryMapper.selectByPrimaryKey(1).getValue();
//        headerMapper.selectByExample();
    }

    public List getHeaderByKeyId(Long headerId) {
        HeaderExample headerExample = new HeaderExample();
        HeaderExample.Criteria criteria = headerExample.createCriteria();
        criteria.andKeyidEqualTo(headerId);
        return headerMapper.selectByExample(headerExample);
    }
}

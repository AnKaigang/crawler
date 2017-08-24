package cn.akgang.service;

import cn.akgang.dao.HeaderMapper;
import cn.akgang.entity.Header;
import cn.akgang.entity.HeaderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by akgang on 2017/7/11.
 */
@Service
public class IndexService {

    @Resource
    private HeaderMapper headerMapper;


    public List<Header> getAllHeaderList() {
        HeaderExample headerExample=new HeaderExample();
        headerExample.getOrderByClause();
        return headerMapper.selectByExample(headerExample);
    }
}

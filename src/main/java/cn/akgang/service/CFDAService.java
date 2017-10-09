package cn.akgang.service;

import cn.akgang.dao.CFDAMapper;
import cn.akgang.entity.CFDA;
import cn.akgang.entity.CFDAExample;
import cn.akgang.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akgang on 2017/10/9.
 */
@Service
public class CFDAService {

    @Autowired
    CFDAMapper cfdaMapper;

    public boolean isExistsByStoreId(String storeId) {
        CFDAExample example = new CFDAExample();
        CFDAExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId);
        List<CFDA> list = cfdaMapper.selectByExample(example);
        if (list.size() > 0 && StringUtils.isNotEmpty(list.get(0).getStoreNo())) {
            return true;
        } else {
            return false;
        }
    }

    public int addNewCFDA(CFDA cfda) {
        return cfdaMapper.insert(cfda);
    }
}

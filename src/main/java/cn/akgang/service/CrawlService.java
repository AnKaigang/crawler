package cn.akgang.service;

import cn.akgang.dao.HeaderMapper;
import cn.akgang.dao.ParamMapper;
import cn.akgang.dao.RequestQueueMapper;
import cn.akgang.entity.RequestQueue;
import cn.akgang.entity.RequestQueueExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akgang on 2017/7/20.
 */
@Service
public class CrawlService {

    @Autowired
    private HeaderMapper headerMapper;

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private RequestQueueMapper requestQueueMapper;

    public void crawl(Long jobId) {
        RequestQueueExample example = new RequestQueueExample();
        RequestQueueExample.Criteria requestQueueExampleCriteria = example.createCriteria();
        requestQueueExampleCriteria.andRequestIdEqualTo(jobId);
        example.setOrderByClause("`sort_num` asc");
        List<RequestQueue> queueList = requestQueueMapper.selectByExample(example);
    }
}

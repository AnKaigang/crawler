package cn.akgang.service;

import cn.akgang.dao.HeaderMapper;
import cn.akgang.dao.ParamMapper;
import cn.akgang.dao.RequestJobMapper;
import cn.akgang.dao.RequestQueueMapper;
import cn.akgang.entity.RequestJob;
import cn.akgang.entity.RequestJobExample;
import cn.akgang.entity.RequestQueue;
import cn.akgang.entity.RequestQueueExample;
import cn.akgang.util.MemCacheUtil;
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

    @Autowired
    private RequestJobMapper requestJobMapper;

    public void crawl(Long jobId) {

    }

    public List<RequestJob> getAllJob() {
        List<RequestJob> allJob = (List<RequestJob>) MemCacheUtil.get("job-list");
        if (allJob == null) {
            allJob = requestJobMapper.selectByExample(new RequestJobExample());
        }
        MemCacheUtil.set("job-list", allJob);
        return allJob;
    }

    public List<RequestQueue> getAllQueueByJob(Long jobId) {
        List<RequestQueue> queueList = (List<RequestQueue>) MemCacheUtil.get("queue-list-jonId-" + jobId);
        if (queueList == null) {
            RequestQueueExample example = new RequestQueueExample();
            RequestQueueExample.Criteria requestQueueExampleCriteria = example.createCriteria();
            requestQueueExampleCriteria.andRequestIdEqualTo(jobId);
            example.setOrderByClause("`sort_num` asc");
            queueList = requestQueueMapper.selectByExample(example);
            MemCacheUtil.set("queue-list-jonId-" + jobId, queueList);
            return queueList;
        } else {
            return queueList;
        }
    }
}

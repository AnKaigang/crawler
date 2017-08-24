package cn.akgang.service;

import cn.akgang.dao.ParamMapper;
import cn.akgang.dao.RequestJobMapper;
import cn.akgang.dao.RequestQueueMapper;
import cn.akgang.entity.*;
import cn.akgang.util.HttpUtil;
import cn.akgang.util.MemCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akgang on 2017/7/20.
 */
@Service
public class CrawlService {

    @Autowired
    private HeaderService headerService;

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private RequestQueueMapper requestQueueMapper;

    @Autowired
    private RequestJobMapper requestJobMapper;

    public void crawl(Long jobId) throws Exception {
        List<RequestQueue> queueList = getAllQueueByJob(jobId);
        for (RequestQueue item : queueList) {
            Map<String, String> header = new HashMap<String, String>();
            if (item.getHeaderId() != null) {
                List<Header> headerList = headerService.getHeaderByKeyId(item.getHeaderId());
                for (Header h : headerList) {
                    header.put(h.getKey(), h.getValue());
                }
            }
            try {


                String result = HttpUtil.sendHttpGet(item.getUrl(), header,  "utf-8");
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public List<RequestJob> getAllJob() {
//        List<RequestJob> allJob = (List<RequestJob>) MemCacheUtil.get("job-list");
        List<RequestJob> allJob = null;
        if (allJob == null) {
            allJob = requestJobMapper.selectByExample(new RequestJobExample());
        }
        MemCacheUtil.set("job-list", allJob);
        return allJob;
    }

    public List<RequestQueue> getAllQueueByJob(Long jobId) {
        List<RequestQueue> queueList = null;
        //(List<RequestQueue>) MemCacheUtil.get("queue-list-jonId-" + jobId);
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

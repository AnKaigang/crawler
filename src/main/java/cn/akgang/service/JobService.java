package cn.akgang.service;

import cn.akgang.dao.RequestJobMapper;
import cn.akgang.entity.RequestJob;
import cn.akgang.entity.RequestJobExample;
import cn.akgang.util.MemCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akgang on 2017/8/14.
 */
@Service
public class JobService {


    @Autowired
    private RequestJobMapper requestJobMapper;


    public List<RequestJob> getAllJob() {
        List<RequestJob> allJob = (List<RequestJob>) MemCacheUtil.get("job-list");
        if (allJob == null) {
            allJob = requestJobMapper.selectByExample(new RequestJobExample());
        }
        MemCacheUtil.set("job-list", allJob);
        return allJob;
    }
}

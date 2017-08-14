package cn.akgang.controller;

import cn.akgang.entity.RequestJob;
import cn.akgang.service.JobService;
import cn.akgang.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by akgang on 2017/8/14.
 */
@Controller
@RequestMapping("job/")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("jobList")
    public JsonResult getJobList() {
        List<RequestJob> jobList = jobService.getAllJob();
        return new JsonResult().set("jobList", jobList);
    }
}

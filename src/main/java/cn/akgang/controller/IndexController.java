package cn.akgang.controller;

import cn.akgang.entity.Header;
import cn.akgang.entity.RequestJob;
import cn.akgang.service.CrawlService;
import cn.akgang.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by akgang on 2017/7/10.
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private CrawlService crawlService;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String index(Model model) {
//        List<Header> headerList = indexService.getAllHeaderList();
        List<RequestJob> jobList = crawlService.getAllJob();
        model.addAttribute("jobList", jobList);
        return "index";
    }
}

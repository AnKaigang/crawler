package cn.akgang.controller;

import cn.akgang.entity.Header;
import cn.akgang.service.CrawlService;
import cn.akgang.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String index() {
        List<Header> headerList=indexService.getAllHeaderList();
        crawlService.crawl(1l);
        return "index";
    }
}

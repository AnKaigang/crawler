package cn.akgang.task;

import cn.akgang.ITask;
import cn.akgang.entity.*;
import cn.akgang.service.DataOperatorService;
import cn.akgang.util.HttpUtil;
import cn.akgang.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author akgang
 * @date 2018/3/27.
 */
@Service
public class HiTask implements ITask {

    private static Logger logger = LoggerFactory.getLogger(HiTask.class);

    @Autowired
    private DataOperatorService dataOperatorService;

    @Override
    public void execute() {
        try {
            int allCount = 0;
            for (int pageIndex = 1; ; ) {
                Map<String, String> header = new HashMap<>();
                header.put("Cookie", "_pk_ses.www.hicloud.com.6882=*; cplang=zh-cn; isPcClientSupport=false; siteID=1; JSESSIONID=70B907734F590DF602F048BBD4E635DB; loginID=70B907734F590DF602F048BBD4E635DB; token=e785bca5c36841cdb51ce91ba5f09a809626b8305168f21c; loginSecLevel=2; isLogin=1; timestampId=6993; CSRFToken=eeda827a859b3183a789a186f670f7b887402fe7b4a52c33; verFlag=1; _pk_id.www.hicloud.com.6882=20b86ece575a769b.1534229079.1.1534230861.1534229079.; JSESSIONID=189C6674AE3C2E8240277A9DBD89E0F8; showKeyOperation=true");
                header.put("Origin", "https://www.hicloud.com");
                header.put("Accept-Encoding", " gzip, deflate, br");
                header.put("Accept-Language", "zh-CN,zh;q=0.9");
                header.put("CSRFToken", "e373bc65e4baf483fe7a93b6e816b983b47109ec5f1a7b9c");
                header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
                header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                header.put("Accept", "application/json, text/javascript, */*; q=0.01");
                header.put("Referer", "https://www.hicloud.com/call");
                header.put("uid", "70086000163146993");
                header.put("X-Requested-With", "XMLHttpRequest");
                header.put("Connection", "keep-alive");
                Map<String, String> param = new HashMap<>();
                Map<String, Object> filter = new HashMap<>();
                param.put("direction", "0");
                param.put("pageIndex", String.valueOf(pageIndex));
                param.put("sortMode", "0");
                param.put("pageSize", null);
                param.put("deviceId", null);

                String result = HttpUtil.sendHttpsPost("https://www.hicloud.com/call/getCallRecord.action?dt=" + System.currentTimeMillis(), header, param, null, "utf-8");
                HiJson listJson = JSON.parseObject(result, HiJson.class);
                if (listJson != null) {
                    if (CollectionUtils.isEmpty(listJson.getCallLogInfos())) {
                        break;
                    } else {
                        pageIndex++;
                    }
                    List<HiCall> callList = listJson.getCallLogInfos();
                    for (HiCall hi : callList) {
                        dataOperatorService.insertEntity(hi);
                        allCount++;

                    }
                } else if (listJson != null) {
                    logger.info("获取hi列表失败：{}", listJson);
                } else {
                    logger.info("获取hi失败：{}", param);
                }
                Random random = new Random();
                int sec = random.nextInt(30);
                System.out.println("---------"+sec+"----------");
                Thread.sleep(sec * 1000);
            }
            System.out.println("------------------------" + allCount + "---------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

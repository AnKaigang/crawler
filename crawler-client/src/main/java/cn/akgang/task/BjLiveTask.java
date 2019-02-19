package cn.akgang.task;

import cn.akgang.ITask;
import cn.akgang.constant.UserAgentConstant;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author akgang
 * @date 2018/3/27.
 */
@Service
public class BjLiveTask implements ITask {

    private static Logger logger = LoggerFactory.getLogger(BjLiveTask.class);

    @Autowired
    private DataOperatorService dataOperatorService;

    @Override
    public void execute() {
        try {
            int allCount = 0;
            for (int pageIndex = 1; ; ) {
                Map<String, String> header = new HashMap<>();

                header.put("Refer", "http://bj.mgzf.com/list/pg1/searchWord?searchWord=");
                header.put("User-Agent", UserAgentConstant.getRandomUA());
                header.put("Origin", "bj.mgzf.com");
                header.put("DNT", "1");
                header.put("Host", "api.mgzf.com");
                header.put("Connection", "keep-alive");
                header.put("Content-Type", "application/x-www-form-urlencoded");
                header.put("Channel", "3");
                header.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                header.put("Accept-Encoding", "gzip, deflate, br");
                header.put("Accept", "application/json, text/plain, */*");
                Map<String, Object> param = new HashMap<>();
                Map<String, Object> filter = new HashMap<>();
                param.put("currentPage", pageIndex);
                param.put("order", null);
                param.put("cityId", 131);
                param.put("showCount", 20);
                param.put("searchWord", null);

                String result = HttpUtil.sendHttpsPost("https://api.mgzf.com/room-find-web/find/list", header, null, JSON.toJSONString(param), "utf-8");
                JuliJson listJson = JSON.parseObject(result, JuliJson.class);
                if (listJson != null && listJson.getCode() == 0) {
                    JuliPage page = JSON.parseObject(listJson.getData(), JuliPage.class);
                    if (page.getHasMore() > 0) {
                        pageIndex++;
                    } else {
                        break;
                    }
                    List<JuliHouseSimple> houseSimpleList = JSON.parseArray(page.getList(), JuliHouseSimple.class);
                    for (JuliHouseSimple houseSimple : houseSimpleList) {
                        HashMap<String, Object> houseParam = new HashMap<>();
                        houseParam.put("project_id", houseSimple.getProjectId());
                        houseParam.put("channel_id", "20000171");
                        houseParam.put("city_id", 20000019);
                        houseParam.put("scode", "");
                        houseParam.put("ts", 0);
                        String houseResult = HttpUtil.sendHttpsPost("https://apiapp.julive.com/v2/project/index", header, null, JSON.toJSONString(houseParam), "utf-8");
                        JuliJson houseJson = JSON.parseObject(houseResult, JuliJson.class);
                        if (houseJson.getCode() != 0) {
                            logger.info("返回房屋信息错误：{}", houseJson);
                        }
                        String info = JSON.parseObject(houseJson.getData()).getString("info");
                        JuLiHouseDetail juLiHouseDetail = JSON.parseObject(info, JuLiHouseDetail.class);
                        List<JuLiHouse> juLiHouseList = JSON.parseArray(juLiHouseDetail.getHouse(), JuLiHouse.class);
                        for (JuLiHouse juLiHouse : juLiHouseList) {
                            JuLiHouseData data = new JuLiHouseData();
                            data.setLat(StringUtils.isNotEmpty(juLiHouseDetail.getLat()) ? Double.valueOf(juLiHouseDetail.getLat()) : null);
                            data.setLng(StringUtils.isNotEmpty(juLiHouseDetail.getLng()) ? Double.valueOf(juLiHouseDetail.getLng()) : null);
                            data.setName(juLiHouseDetail.getName());
                            data.setPrice(Double.valueOf(juLiHouse.getOfferPrice()));
                            JSONArray jsonArray = JSON.parseArray(juLiHouse.getRoomType());
                            data.setRoomType(jsonArray.getInteger(0));
                            dataOperatorService.insertEntity(data);
                            allCount++;
                        }

                    }
                } else if (listJson != null) {
                    logger.info("获取居理新房列表失败：{}", listJson.getErrMsg());
                } else {
                    logger.info("获取居理新房列表失败：{}", param);
                }
            }
            System.out.println("------------------------" + allCount + "---------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

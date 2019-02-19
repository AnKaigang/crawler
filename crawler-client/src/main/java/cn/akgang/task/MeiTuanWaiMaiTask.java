package cn.akgang.task;

import cn.akgang.ITask;
import cn.akgang.constant.UserAgentConstant;
import cn.akgang.entity.Food;
import cn.akgang.entity.Store;
import cn.akgang.service.DataOperatorService;
import cn.akgang.util.ElementsUtil;
import cn.akgang.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author akgang
 * @date 2017/9/27
 */
@Service
public class MeiTuanWaiMaiTask implements ITask {

    @Autowired
    private DataOperatorService dataOperatorService;

    @Override
    public void execute() {
        try {
            Map<String, String> header = new HashMap<String, String>();
            header.put("User-Agent", UserAgentConstant.getRandomUA());
            header.put("Host", "waimai.meituan.com");
            header.put("Referer", "https://www.google.co.jp/");
            header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
            header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            header.put("Accept-Encoding", "gzip, deflate");
            header.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
            header.put("Cache-Control", "max-age=0");
            header.put("Connection", "Keep-Alive");
            header.put("Upgrade-Insecure-Requests:", "1");
            header.put("Content-Type", "application/x-www-form-urlencoded;");
            header.put("Cookie", "w_uuid=MpuOZ9Kup7dc8FLbxtS8OS_jbqxzvInU6ZIYMcFDXMSe1aduU1nIUuj5lgpbDKSZ; rvct=1; _lxsdk_cuid=15e12e68571c8-0ba2a5998a45d3-143a6d54-fa000-15e12e68572c8; __mta=142890890.1504059316715.1504059320907.1504059324184.3; rvd=39616656; __utma=211559370.749797392.1503555652.1503555652.1504059317.2; __utmz=211559370.1504059317.2.2.utmcsr=baidu|utmccn=baidu|utmcmd=organic|utmcct=homepage; __utmv=211559370.|1=city=beijing=1^3=dealtype=163=1; _ga=GA1.2.446093843.1503474503; uuid=2d1a5e59f69e5ec22fda.1503555650.0.0.0; oc=VahOfFe24KgKMdniv1YrJuNTAUYUS_IfFAOW9Y9nhZNI0NAbXdSx5kFRY_4OEq_zN2jlkAcymc6k33LLetsobFEUV0Bpy5TQD0MvmwTa2Ha3CFMJNxHdnIRpCx8rJHcBnSWBK0Vn9GpTyV5tLBR1GQPXY2fFwOzSjbDgHjl034M; ci=1; w_cid=130184; w_cpy_cn=\"%E6%96%B0%E4%B9%90%E5%B8%82\"; w_cpy=xinleshi; waddrname=\"%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; w_geoid=wwcd4tduky62; w_ah=\"38.35128799080849,114.72322169691324,%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; JSESSIONID=12ruup637r9ol1q31bj7kc6o2z; _ga=GA1.3.446093843.1503474503; _gid=GA1.3.1511211026.1505983224; _gat=1; __mta=142890890.1504059316715.1504059324184.1505990124018.4; w_utmz=\"utm_campaign=(direct)&utm_source=(direct)&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=a2b05f32-10f3-4005-bac7-7ded45cbadfa");
            String result = HttpUtil.sendHttpGet("http://waimai.meituan.com/home/wwcd4tduky62", header, "utf-8");
            Document document = Jsoup.parse(result);
            Elements itemElements = document.select("a.rest-atag");
            Random random = new Random();
            for (Element item : itemElements) {
                String storeName = "无";
                try {
                    storeName = !ElementsUtil.isEmpty(item.select("div.name")) ? item.select("div.name").get(0).select("span").get(0).html() : "无";
                } catch (Exception e) {

                }
                String score = "无";
                try {
                    score = item.select("div.rank").get(0).select("span.score-num").get(0).html();
                } catch (Exception e) {

                }
                int index = 0;
                try {
                    index = item.select("div.rank").html().indexOf("月售");

                } catch (Exception e) {

                }
                String monthCount = "无";
                try {
                    monthCount = item.select("div.rank").html().substring(index - 2, index + 8);
                } catch (Exception e) {

                }
                String startPrice = "无";
                try {
                    startPrice = item.select("div.price").get(0).select("span.start-price").html();
                } catch (Exception e) {

                }
                String deliverPrice = "无";
                try {
                    deliverPrice = item.select("div.price").get(0).select("span.send-price").html();
                } catch (Exception e) {

                }
                String deliverTime = "无";
                try {
                    deliverTime = item.select("div.price").get(0).select("span.send-time").get(0).select("i").get(0).html();
                } catch (Exception e) {

                }
                Date nowDate = new Date();
                Store store = new Store();
                store.setStoreName(storeName);
                store.setScore(score);
                store.setCreatedAt(nowDate);
                store.setUpdatedAt(nowDate);
                store.setMonthCount(monthCount);
                store.setStartPrice(startPrice);
                store.setDeliverPrice(deliverPrice);
                store.setDeliverTime(deliverTime);
                dataOperatorService.insertEntity(store);
                String subHref = item.attr("href");
                String href = "http://waimai.meituan.com/" + subHref;
                Integer sleepTime = random.nextInt(10);
                while (sleepTime == 0) {
                    sleepTime = random.nextInt(10);
                }
                Thread.sleep(sleepTime * 1000L);
                String childResult = HttpUtil.sendHttpGet(href, header, "utf-8");
                Document childDocument = Jsoup.parse(childResult);
                Elements foodElements = childDocument.select("div.j-pic-food");
                for (Element food : foodElements) {
                    String foodName = "";
                    try {
                        foodName = food.select("div.np").get(0).select("span.name").get(0).html();
                    } catch (Exception e) {

                    }
                    String saleCount = "0";
                    try {
                        saleCount = food.select("div.sale-info").get(0).select("div.sold-count").get(0).select("span").get(0).html();

                    } catch (Exception e) {

                    }
                    String sureCount = "0";
                    try {
                        sureCount = food.select("div.sale-info").get(0).select("div.zan-count").get(0).html();
                    } catch (Exception e) {

                    }
                    String price = "0.00";
                    try {
                        price = food.select("div.labels").get(0).select("div.price").get(0).select("div.only").get(0).html();
                    } catch (Exception e) {

                    }
                    Food tmpFood = new Food();
                    tmpFood.setStoreId(store.getId());
                    tmpFood.setFoodName(foodName);
                    tmpFood.setSaleCount(saleCount);
                    tmpFood.setSureCount(sureCount);
                    tmpFood.setPrice(price);
                    tmpFood.setCreatedAt(nowDate);
                    tmpFood.setUpdatedAt(nowDate);
                    tmpFood.setStore(store);
                    dataOperatorService.insertEntity(tmpFood);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

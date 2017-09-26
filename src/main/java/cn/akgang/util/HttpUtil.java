package cn.akgang.util;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * Created by akgang on 2017/5/25.
 * <p>
 * 发送http(s)请求的工具类包
 */
public class HttpUtil {

    static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    /**
     * 发送http的post请求
     *
     * @param url
     * @param header
     * @param paramMap
     * @param jsonBody
     * @param charset
     * @return
     * @throws IOException
     */
    public static String sendHttpPost(String url, Map<String, String> header, Map<String, String> paramMap, String jsonBody, String charset) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = generatorPostRequest(url, header, paramMap, jsonBody, charset);
        String result = null;
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 发送https的post请求
     *
     * @param url
     * @param paramMap
     * @param jsonBody
     * @param charset
     * @return
     */
    public static String sendHttpsPost(String url, Map<String, String> header, Map<String, String> paramMap, String jsonBody, String charset) throws Exception {
        HttpClient httpClient = new SSLClient();
        HttpPost httpsPost = generatorPostRequest(url, header, paramMap, jsonBody, charset);
        String result = null;
        HttpResponse response = httpClient.execute(httpsPost);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
        }
        return result;
    }

    /**
     * 发送http的get请求
     *
     * @param url
     * @param header
     * @param charset
     * @return
     * @throws IOException
     */
    public static String sendHttpGet(String url, Map<String, String> header, String charset) throws IOException {
//        Document doc= Jsoup.connect(url).post();

        HttpGet httpGet = generatorGetRequest(url, header);
        HttpClient httpClient = new DefaultHttpClient();
        String result = null;
//        httpClient.getParams().setPsetCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
            System.out.println(result);
        }
        return result;
    }

    private static HttpEntity processResponse(HttpResponse response) {
        if (response.getEntity().getContentEncoding() == null) {
            return response.getEntity();
        }
        String responseValue = response.getEntity().getContentEncoding().getValue();
        if ("gzip".equals(responseValue)) {
            return new GzipDecompressingEntity(response.getEntity());
        } else {
            return response.getEntity();
        }
    }

    /**
     * 发送https的get请求
     *
     * @param url
     * @param header
     * @param charset
     * @return
     * @throws Exception
     */
    public static String sendHttpsGet(String url, Map<String, String> header, String charset) throws Exception {
        HttpClient httpClient = new SSLClient();
        HttpGet httpGet = generatorGetRequest(url, header);
        String result = null;
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
        }
        return result;
    }

    /**
     * 生成post请求格式
     *
     * @param url
     * @param header
     * @param paramMap
     * @param jsonBody
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpPost generatorPostRequest(String url, Map<String, String> header, Map<String, String> paramMap, String jsonBody, String charset) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        Map<String, String> defaultHeader = new HashMap<String, String>();
//        Accept:*/*

        defaultHeader.put("Host", "app1.sfda.gov.cn");
        defaultHeader.put("Origin", "http://app1.sfda.gov.cn");
        defaultHeader.put("Referer", "http://app1.sfda.gov.cn/datasearch/face3/base.jsp?tableId=24&tableName=TABLE24&title=GSP%C8%CF%D6%A4&bcId=118715593187347941914723540896");
        defaultHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Encoding", "gzip, deflate");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        defaultHeader.put("Cache-Control", "no-cache");
        defaultHeader.put("Connection", "Keep-Alive");
        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
        defaultHeader.put("Content-Type", "application/x-www-form-urlencoded;");
        defaultHeader.put("Cookie", "JSESSIONID=6C0EAC78128E20A65CCB1A8AB3755E83.7; FSSBBIl1UgzbN7N80T=1f6KyRoZ2ewEcmiCTZ8pXsPERbS7G7XNAgI1Zoyka07cntux4xWCuzuoJVZHzzIwIRH42zrIKutR.0E0KWyCJjT6Jp5DHvmlFUM1D0d.sw.5tarOzNC2QToW1w8lcsyHNgEVal708lV_wUSyRqy5Kg5nFCJ3oHEBIK9OwWUsgbZNVDq; FSSBBIl1UgzbN7N80S=wdyNt8FP1MR4F1_g9zl4CMEJClxtuaMvzf26C_CvWoW7fJUrv0MbN7al4.NLCVAz; _gscu_1586185021=06342291xdo2la20; _gscbrs_1586185021=1; _gscu_1358151024=06346012o2nbav42; _gscs_1358151024=06346012rsxe6h42|pv:5; _gscbrs_1358151024=1; yunsuo_session_verify=71537107a85fd749a2c078615bfbd4b0");
        defaultHeader.putAll(header);
        //设置请求头
        if (defaultHeader != null) {
            for (String key : defaultHeader.keySet()) {
                httpPost.setHeader(key, defaultHeader.get(key));
            }
        }

        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                list.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
            httpPost.setEntity(entity);
        }
        //设置请求体
        if (!StringUtils.isEmpty(jsonBody)) {
            StringEntity stringEntity = new StringEntity(jsonBody, charset);
            httpPost.setEntity(stringEntity);
        }
        return httpPost;
    }

    /**
     * 生成get请求格式
     *
     * @param url
     * @param header
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpGet generatorGetRequest(String url, Map<String, String> header) throws UnsupportedEncodingException {
        Map<String, String> defaultHeader = new HashMap<String, String>();
//        Accept:*/*

        defaultHeader.put("Host", "app1.sfda.gov.cn");
        defaultHeader.put("Origin", "http://app1.sfda.gov.cn");
        defaultHeader.put("Referer", "http://app1.sfda.gov.cn/datasearch/face3/base.jsp?tableId=24&tableName=TABLE24&title=GSP%C8%CF%D6%A4&bcId=118715593187347941914723540896");
        defaultHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Encoding", "gzip, deflate");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        defaultHeader.put("Cache-Control", "no-cache");
        defaultHeader.put("Connection", "Keep-Alive");
        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
        defaultHeader.put("Content-Type", "application/x-www-form-urlencoded;");
//        defaultHeader.put("slua", "a45j9qzTyBT76EXEO+99B6ZI5Hs4izxTuaxEwO0Behh/MX0hhQDzhViUN1BPoFiY/joAFNn/bYJeMCtHalQxEdSHngQvZCQMAFQaUsd35XiugIyBBz8OVxzX9W1OHJIjDpwSj9+1gudxT+eEsLuNYrd+YnrMcjP/+2hO//6nrXcpxm3B2sWZu/26niAyNW886nknIH6qZyViAuCwlQ9t0xRjhSHP6OkOXucbAM3IIctiM3g255J3MaM4igLdD9qefVGfBmvoUUNjlGQ7sRQClmNAgSNYfVa0q37vpIXUrN/wSJmQJoKoa4iVbDYMoNsygs4yr3/sWyIMFgTAApWtuVKDcC46E6K4u+lfqx4MT/BHLRSQmXeOaBfAy9i+yxfSR+5RsK5KFGPC+1CDPVpnkEMgFI/eU7qIwH34x8esiTw76ABBT7sicOKPnL26qN+6buI4AjCoP117FYJjaXJH5IgEY2TZ0CYVCBHTJ/NxHd9iHkNs+PdjVJt0gBZihJWKOFolae/7af52AL0fCX26eqmkgd2wraAQkmBWTIbjWGOjK7YZK5PVmZGpb1Bw3F5SjT6Jv5/5gQoRPtARAlTxSzHcjrPyv3mS5fpk85y0yz2UYAR5Hk7w2urGvwMNMHz5/duATsz/CbLXFGtp0ObnGIlKfZGWu0RnxLpsExMhOLY=");

//        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
        defaultHeader.put("Cookie", "JSESSIONID=6C0EAC78128E20A65CCB1A8AB3755E83.7; FSSBBIl1UgzbN7N80T=1f6KyRoZ2ewEcmiCTZ8pXsPERbS7G7XNAgI1Zoyka07cntux4xWCuzuoJVZHzzIwIRH42zrIKutR.0E0KWyCJjT6Jp5DHvmlFUM1D0d.sw.5tarOzNC2QToW1w8lcsyHNgEVal708lV_wUSyRqy5Kg5nFCJ3oHEBIK9OwWUsgbZNVDq; FSSBBIl1UgzbN7N80S=wdyNt8FP1MR4F1_g9zl4CMEJClxtuaMvzf26C_CvWoW7fJUrv0MbN7al4.NLCVAz; _gscu_1586185021=06342291xdo2la20; _gscbrs_1586185021=1; _gscu_1358151024=06346012o2nbav42; _gscs_1358151024=06346012rsxe6h42|pv:5; _gscbrs_1358151024=1; yunsuo_session_verify=71537107a85fd749a2c078615bfbd4b0");
        defaultHeader.putAll(header);
        HttpGet httpGet = new HttpGet(url);
//        httpGe
        //设置请求头
        if (defaultHeader != null) {
            for (String key : defaultHeader.keySet()) {
                httpGet.setHeader(key, defaultHeader.get(key));
            }
        }
        return httpGet;
    }

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InterruptedException {
        File file = new File("content" + DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        if (!file.exists()) {
            file.createNewFile();
        }
        List<String> uaList=new LinkedList<String>();
        uaList.add("Mozilla/5.0(Macintosh;U;IntelMacOSX10_6_8;en-us)AppleWebKit/534.50(KHTML,likeGecko)Version/5.1Safari/534.50");
        uaList.add("Mozilla/5.0(Windows;U;WindowsNT6.1;en-us)AppleWebKit/534.50(KHTML,likeGecko)Version/5.1Safari/534.50");
        uaList.add("Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;Trident/5.0");
        uaList.add("Mozilla/4.0(compatible;MSIE8.0;WindowsNT6.0;Trident/4.0)");
        uaList.add("Mozilla/5.0(Macintosh;IntelMacOSX10.6;rv:2.0.1)Gecko/20100101Firefox/4.0.1");
        uaList.add("Mozilla/5.0(WindowsNT6.1;rv:2.0.1)Gecko/20100101Firefox/4.0.1");
        uaList.add("Opera/9.80(Macintosh;IntelMacOSX10.6.8;U;en)Presto/2.8.131Version/11.11");
        uaList.add("Opera/9.80(WindowsNT6.1;U;en)Presto/2.8.131Version/11.11");
        uaList.add("Mozilla/5.0(Macintosh;IntelMacOSX10_7_0)AppleWebKit/535.11(KHTML,likeGecko)Chrome/17.0.963.56Safari/535.11");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1;Maxthon2.0)");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1;TencentTraveler4.0)");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1;TheWorld)");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1;360SE)");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1)");
        uaList.add("Mozilla/4.0(compatible;MSIE7.0;WindowsNT5.1;AvantBrowser)");
        String fileContent = "";
        for (int i = 1; i<100000; i++) {
            int index= (int) (Math.random()*10000);
            Map<String, String> header = new HashMap<String, String>();
            header.put("cache-control","no-cache");
            header.put("Content-Type","application/x-www-form-urlencoded");
            int uaIndex= (int) (Math.random()*uaList.size());
            header.put("User-Agent",uaList.get(uaIndex));
//
            Map<String, String> param = new HashMap<String, String>();
            param.put("tableId", "24");
            param.put("State", "1");
            param.put("bcId", "118715593187347941914723540896");
            param.put("curstart", String.valueOf(index));
            param.put("tableName", "TABLE24");
            param.put("viewtitleName", "COLUMN159");
            param.put("viewsubTitleName", "COLUMN158");
            param.put("tableView", "GSP%25E8%25AE%25A4%25E8%25AF%2581");
            param.put("cid", "0");
            param.put("ytableId", "0");
            param.put("searchType", "search");

            String resultStr = HttpUtil.sendHttpPost("http://app1.sfda.gov.cn/datasearch/face3/search.jsp", header, param, null, "utf-8");
            Document document = Jsoup.parse(resultStr);
            Elements arrayA = document.select("a[href]");
            if (arrayA == null || arrayA.size() == 0) {
                System.out.println(document.body().html());
                continue;
            }
            for (Element element : arrayA) {
                String href = element.attr("href");
                href = href.replace("javascript:commitForECMA(callbackC,'", "");
                href = href.replace("',null)", "");
                uaIndex= (int) (Math.random()*uaList.size());
                header.put("User-Agent",uaList.get(uaIndex));
                String contentHtml = HttpUtil.sendHttpGet("http://app1.sfda.gov.cn/datasearch/face3/" + href, header, "utf-8");
                Document contentDocument = Jsoup.parse(contentHtml);
                Elements contentArray = contentDocument.select(".listmain tr");
                for (Element elementTr : contentArray) {
                    Elements elementsTd = elementTr.getElementsByTag("td");
                    if (elementsTd != null && elementsTd.size() >= 2&&StringUtils.isNotEmpty(elementsTd.get(0).html())) {
                        fileContent += elementsTd.get(0).html() + ":";
                        fileContent += elementsTd.get(1).html() + "\n";
                    }
                }
            }
            fileContent = fileContent + "\n" + "\n" + "\n";
            Thread.sleep(5000);
        }
        FileUtils.writeStringToFile(file, fileContent, "utf-8");

    }

}

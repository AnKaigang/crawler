package cn.akgang.util;

import cn.akgang.entity.CFDA;
import cn.akgang.service.CFDAService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    public static String sendHttpPost(String url, Map<String, String> header, Map<String, String> paramMap, String jsonBody, String charset) throws IOException, InterruptedException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = generatorPostRequest(url, header, paramMap, jsonBody, charset);
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpPost.setConfig(config);
        String result = null;
        HttpResponse response = null;
        int count = 1;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
        }
        while (response == null || 200 != response.getStatusLine().getStatusCode()) {
            System.out.println("失败重试第" + count++ + "次");
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
            try {
                response = httpClient.execute(httpPost);
            } catch (Exception e) {
            }
            Thread.sleep(500);
        }
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
    public static String sendHttpGet(String url, Map<String, String> header, String charset) throws IOException, InterruptedException {
        HttpGet httpGet = generatorGetRequest(url, header);
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpGet.setConfig(config);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpResponse response = null;
        int count = 1;
        try {
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
        }
        while (response == null || 200 != response.getStatusLine().getStatusCode()) {
            System.out.println("失败重试第" + count++ + "次");
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
            try {
                response = httpClient.execute(httpGet);
            } catch (Exception e) {
            }
            Thread.sleep(500);
        }
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
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        if (defaultHeader != null) {
            for (String key : defaultHeader.keySet()) {
                httpGet.setHeader(key, defaultHeader.get(key));
            }
        }
        return httpGet;
    }

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InterruptedException {
        CFDAService cfdaService = (CFDAService) context.getBean("CFDAService");
        //todo 如果想增加线程数，请更改此参数
        Executor preCacheExecutor = Executors.newFixedThreadPool(4);
        for (int i = 1; i < 100000; i++) {
            int index = (int) (Math.random() * 10000);
            preCacheExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    List<String> uaList = new LinkedList<String>();
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

                    Map<String, String> header = new HashMap<String, String>();
                    header.put("cache-control", "no-cache");
                    header.put("Content-Type", "application/x-www-form-urlencoded");
                    int uaIndex = (int) (Math.random() * uaList.size());
                    header.put("User-Agent", uaList.get(uaIndex));
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

                    String resultStr = null;
                    try {
                        resultStr = HttpUtil.sendHttpPost("http://app1.sfda.gov.cn/datasearch/face3/search.jsp", header, param, null, "utf-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    Document document = Jsoup.parse(resultStr);
                    Elements arrayA = document.select("a[href]");
                    if (arrayA == null || arrayA.size() == 0) {
                        System.out.println(document.body().html());
                        return;
                    }
                    for (Element element : arrayA) {
                        String href = element.attr("href");
                        String title = element.html();
                        String id = title.split("\\.")[0];
                        String name = title.split("\\.")[1];
                        if (!cfdaService.isExistsByStoreId(id)) {
                            CFDA cfda = new CFDA();
                            cfda.setStoreId(id);
                            cfda.setStoreName(name);
                            href = href.replace("javascript:commitForECMA(callbackC,'", "");
                            href = href.replace("',null)", "");
                            String contentHtml = null;
                            try {
                                contentHtml = HttpUtil.sendHttpGet("http://app1.sfda.gov.cn/datasearch/face3/" + href, header, "utf-8");
                            } catch (IOException e) {
                                e.printStackTrace();
                                continue;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                continue;
                            }
                            Document contentDocument = Jsoup.parse(contentHtml);
                            Elements contentArray = contentDocument.select(".listmain tr");
                            for (Element elementTr : contentArray) {
                                Elements elementsTd = elementTr.getElementsByTag("td");
                                if (elementsTd != null && elementsTd.size() >= 2 && StringUtils.isNotEmpty(elementsTd.get(0).html())) {
                                    cfda = setCFDAPropertity(cfda, elementsTd.get(0).html(), elementsTd.get(1).html());
                                }
                            }
                            cfdaService.addNewCFDA(cfda);
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;

                    }
                }
            });
        }


    }

    private static CFDA setCFDAPropertity(CFDA cfda, String name, String value) {
        if (StringUtils.isNotEmpty(name)) {
            switch (name) {
                case "省市":
                    cfda.setStoreProvince(value);
                    break;
                case "证书编号":
                    cfda.setStoreNo(value);
                    break;
                case "地址":
                    cfda.setStoreAddress(value);
                    break;
                case "认证范围":
                    cfda.setStoreRange(value);
                    break;
                case "发证时间":
                    cfda.setStroreOpenDate(value);
                    break;
                case "有效期截止日":
                    cfda.setStoreExpireDate(value);
                    break;
                case "备注":
                    cfda.setStoreBackup(value);
                    break;
                case "注":
                    cfda.setStoreAttention(value);
                    break;
                default:
                    break;
            }
        }
        return cfda;
    }

}

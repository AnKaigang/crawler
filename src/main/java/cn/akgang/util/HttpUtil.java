package cn.akgang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akgang on 2017/5/25.
 * <p>
 * 发送http(s)请求的工具类包
 */
public class HttpUtil {


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
        Document doc= Jsoup.connect(url).post();

        HttpGet httpGet = generatorGetRequest(url, header);
        HttpClient httpClient = new DefaultHttpClient();
        String result = null;
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
        }
        return result;
    }

    private static HttpEntity processResponse(HttpResponse response) {
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
        //设置请求头
        if (header != null) {
            for (String key : header.keySet()) {
                httpPost.setHeader(key, header.get(key));
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
        defaultHeader.put("Host", "meituan.com");
        defaultHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        defaultHeader.put("Accept-Encoding", "gzip, deflate");
        defaultHeader.put("Referer", "http://waimai.meituan.com/");
//        defaultHeader.put("connection", "keep-alive");
//        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
//        defaultHeader.put("Cookie", "w_uuid=MpuOZ9Kup7dc8FLbxtS8OS_jbqxzvInU6ZIYMcFDXMSe1aduU1nIUuj5lgpbDKSZ; w_cid=130184; w_cpy_cn=\"%E6%96%B0%E4%B9%90%E5%B8%82\"; w_cpy=xinleshi; waddrname=\"%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; w_geoid=wwcd4tduky62; w_ah=\"38.35128799080849,114.72322169691324,%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; JSESSIONID=18voafy7waemthktx11iek0nv; _ga=GA1.3.446093843.1503474503; _gid=GA1.3.1678988025.1503474503; __mta=213799467.1503474502742.1503474507155.1503475029641.3; w_utmz=\"utm_campaign=(direct)&utm_source=(direct)&utm_medium=(none)&utm_content=(none)&utm_term=(none)");
        defaultHeader.putAll(header);
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        if (header != null) {
            for (String key : defaultHeader.keySet()) {
                httpGet.setHeader(key, defaultHeader.get(key));
            }
        }
        return httpGet;
    }
}

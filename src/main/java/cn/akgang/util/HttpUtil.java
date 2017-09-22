package cn.akgang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
        defaultHeader.put("Host", "waimai.meituan.com");
        defaultHeader.put("Referer", "https://www.google.co.jp/");
        defaultHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Encoding", "gzip, deflate");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        defaultHeader.put("Cache-Control", "max-age=0");
        defaultHeader.put("Connection", "Keep-Alive");
        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
        defaultHeader.put("Content-Type", "application/x-www-form-urlencoded;");
//        defaultHeader.put("slua", "a45j9qzTyBT76EXEO+99B6ZI5Hs4izxTuaxEwO0Behh/MX0hhQDzhViUN1BPoFiY/joAFNn/bYJeMCtHalQxEdSHngQvZCQMAFQaUsd35XiugIyBBz8OVxzX9W1OHJIjDpwSj9+1gudxT+eEsLuNYrd+YnrMcjP/+2hO//6nrXcpxm3B2sWZu/26niAyNW886nknIH6qZyViAuCwlQ9t0xRjhSHP6OkOXucbAM3IIctiM3g255J3MaM4igLdD9qefVGfBmvoUUNjlGQ7sRQClmNAgSNYfVa0q37vpIXUrN/wSJmQJoKoa4iVbDYMoNsygs4yr3/sWyIMFgTAApWtuVKDcC46E6K4u+lfqx4MT/BHLRSQmXeOaBfAy9i+yxfSR+5RsK5KFGPC+1CDPVpnkEMgFI/eU7qIwH34x8esiTw76ABBT7sicOKPnL26qN+6buI4AjCoP117FYJjaXJH5IgEY2TZ0CYVCBHTJ/NxHd9iHkNs+PdjVJt0gBZihJWKOFolae/7af52AL0fCX26eqmkgd2wraAQkmBWTIbjWGOjK7YZK5PVmZGpb1Bw3F5SjT6Jv5/5gQoRPtARAlTxSzHcjrPyv3mS5fpk85y0yz2UYAR5Hk7w2urGvwMNMHz5/duATsz/CbLXFGtp0ObnGIlKfZGWu0RnxLpsExMhOLY=");

//        defaultHeader.put("Upgrade-Insecure-Requests:", "1");
        defaultHeader.put("Cookie", "w_uuid=MpuOZ9Kup7dc8FLbxtS8OS_jbqxzvInU6ZIYMcFDXMSe1aduU1nIUuj5lgpbDKSZ; rvct=1; _lxsdk_cuid=15e12e68571c8-0ba2a5998a45d3-143a6d54-fa000-15e12e68572c8; __mta=142890890.1504059316715.1504059320907.1504059324184.3; rvd=39616656; __utma=211559370.749797392.1503555652.1503555652.1504059317.2; __utmz=211559370.1504059317.2.2.utmcsr=baidu|utmccn=baidu|utmcmd=organic|utmcct=homepage; __utmv=211559370.|1=city=beijing=1^3=dealtype=163=1; _ga=GA1.2.446093843.1503474503; uuid=2d1a5e59f69e5ec22fda.1503555650.0.0.0; oc=VahOfFe24KgKMdniv1YrJuNTAUYUS_IfFAOW9Y9nhZNI0NAbXdSx5kFRY_4OEq_zN2jlkAcymc6k33LLetsobFEUV0Bpy5TQD0MvmwTa2Ha3CFMJNxHdnIRpCx8rJHcBnSWBK0Vn9GpTyV5tLBR1GQPXY2fFwOzSjbDgHjl034M; ci=1; w_cid=130184; w_cpy_cn=\"%E6%96%B0%E4%B9%90%E5%B8%82\"; w_cpy=xinleshi; waddrname=\"%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; w_geoid=wwcd4tduky62; w_ah=\"38.35128799080849,114.72322169691324,%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; JSESSIONID=12ruup637r9ol1q31bj7kc6o2z; _ga=GA1.3.446093843.1503474503; _gid=GA1.3.1511211026.1505983224; _gat=1; __mta=142890890.1504059316715.1504059324184.1505990124018.4; w_utmz=\"utm_campaign=(direct)&utm_source=(direct)&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=a2b05f32-10f3-4005-bac7-7ded45cbadfa");
//        defaultHeader.putAll(header);
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

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
//         Map<String, String> param = new HashMap<String, String>();
        HttpUtil.sendHttpGet("http://waimai.meituan.com/home/wwcd4tduky62", null, "utf-8");

    }

}

package cn.akgang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author akgang
 * @date 2017/5/25
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
    public static String sendHttpPost(String url, Map<String, String> header, Map<String, String> paramMap, String jsonBody, String charset) throws IOException, InterruptedException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = generatorPostRequest(url, header, paramMap, jsonBody, charset);
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpPost.setConfig(config);
        String result = null;
        HttpResponse response = resumeRequest(httpPost, null, httpClient);
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
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build();
        HttpPost httpsPost = generatorPostRequest(url, header, paramMap, jsonBody, charset);
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpsPost.setConfig(config);
        String result = null;
        HttpResponse response = resumeRequest(httpsPost, null, httpClient);
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = generatorGetRequest(url, header);
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpGet.setConfig(config);
        String result = null;
        HttpResponse response = resumeRequest(null, httpGet, httpClient);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
            System.out.println(result);
        }
        return result;
    }

    private static HttpResponse resumeRequest(HttpPost httpPost, HttpGet httpGet, CloseableHttpClient httpClient) throws IOException, InterruptedException {
        HttpResponse response = httpClient.execute(httpGet == null ? httpPost : httpGet);
        int count = 0;
        while (response == null || 200 != response.getStatusLine().getStatusCode()) {
            System.out.println("第" + ++count + "次尝试");
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
            try {
                response = httpClient.execute(httpGet);
            } catch (Exception e) {
            }
            Thread.sleep(500);
        }
        return response;
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
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build();
        HttpGet httpGet = generatorGetRequest(url, header);
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        httpGet.setConfig(config);
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

        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        defaultHeader.put("Accept-Encoding", "gzip, deflate");
        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        defaultHeader.put("Connection", "Keep-Alive");
        defaultHeader.put("Content-Type", "application/x-www-form-urlencoded;");
        if (header != null) {
            defaultHeader.putAll(header);
        }
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
        defaultHeader.put("Cookie", "w_uuid=MpuOZ9Kup7dc8FLbxtS8OS_jbqxzvInU6ZIYMcFDXMSe1aduU1nIUuj5lgpbDKSZ; rvct=1; _lxsdk_cuid=15e12e68571c8-0ba2a5998a45d3-143a6d54-fa000-15e12e68572c8; __mta=142890890.1504059316715.1504059320907.1504059324184.3; rvd=39616656; __utma=211559370.749797392.1503555652.1503555652.1504059317.2; __utmz=211559370.1504059317.2.2.utmcsr=baidu|utmccn=baidu|utmcmd=organic|utmcct=homepage; __utmv=211559370.|1=city=beijing=1^3=dealtype=163=1; _ga=GA1.2.446093843.1503474503; uuid=2d1a5e59f69e5ec22fda.1503555650.0.0.0; oc=VahOfFe24KgKMdniv1YrJuNTAUYUS_IfFAOW9Y9nhZNI0NAbXdSx5kFRY_4OEq_zN2jlkAcymc6k33LLetsobFEUV0Bpy5TQD0MvmwTa2Ha3CFMJNxHdnIRpCx8rJHcBnSWBK0Vn9GpTyV5tLBR1GQPXY2fFwOzSjbDgHjl034M; ci=1; w_cid=130184; w_cpy_cn=\"%E6%96%B0%E4%B9%90%E5%B8%82\"; w_cpy=xinleshi; waddrname=\"%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; w_geoid=wwcd4tduky62; w_ah=\"38.35128799080849,114.72322169691324,%E6%B2%B3%E5%8C%97%E7%BE%8E%E6%9C%AF%E5%AD%A6%E9%99%A2\"; JSESSIONID=12ruup637r9ol1q31bj7kc6o2z; _ga=GA1.3.446093843.1503474503; _gid=GA1.3.1511211026.1505983224; _gat=1; __mta=142890890.1504059316715.1504059324184.1505990124018.4; w_utmz=\"utm_campaign=(direct)&utm_source=(direct)&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=a2b05f32-10f3-4005-bac7-7ded45cbadfa");
        if (header != null) {
            defaultHeader.putAll(header);
        }
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        if (defaultHeader != null) {
            for (String key : defaultHeader.keySet()) {
                httpGet.setHeader(key, defaultHeader.get(key));
            }
        }
        return httpGet;
    }

    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InterruptedException {

        Thread.sleep(5000);
    }

}

package cn.akgang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by akgang on 2017/5/25.
 */
public class HttpUtil {


    public String sendHttpPost() {

        return "";
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
    public String sendHttpsPost(String url, Map<String, String> paramMap, String jsonBody, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content type", "application/json");
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            if (paramMap != null) {
                Iterator iterator = paramMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> elem = (Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
                }
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            if (!StringUtils.isEmpty(jsonBody)) {
                StringEntity stringEntity = new StringEntity(jsonBody, charset);
                httpPost.setEntity(stringEntity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String sendHttpGet(String url) {
        HttpPost httpPost=new HttpPost(url);

        return "";
    }

    public String sendHttpsGet() {
        return "";
    }
}

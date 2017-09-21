package cn.akgang.util;

import cn.akgang.entity.Store;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
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
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity resEntity = processResponse(response);
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
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
        defaultHeader.put("Host", "wmapi.meituan.com");
        defaultHeader.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 6.0; Redmi Note 4 MIUI/V8.5.5.0.MBFCNED)");
//        defaultHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        defaultHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        defaultHeader.put("Accept-Encoding", "gzip");
        defaultHeader.put("Referer", "http://waimai.meituan.com/");
        defaultHeader.put("Connection", "Keep-Alive");
        defaultHeader.put("Content-Type", "application/x-www-form-urlencoded;");
        defaultHeader.put("slua", "a45j9qzTyBT76EXEO+99B6ZI5Hs4izxTuaxEwO0Behh/MX0hhQDzhViUN1BPoFiY/joAFNn/bYJeMCtHalQxEdSHngQvZCQMAFQaUsd35XiugIyBBz8OVxzX9W1OHJIjDpwSj9+1gudxT+eEsLuNYrd+YnrMcjP/+2hO//6nrXcpxm3B2sWZu/26niAyNW886nknIH6qZyViAuCwlQ9t0xRjhSHP6OkOXucbAM3IIctiM3g255J3MaM4igLdD9qefVGfBmvoUUNjlGQ7sRQClmNAgSNYfVa0q37vpIXUrN/wSJmQJoKoa4iVbDYMoNsygs4yr3/sWyIMFgTAApWtuVKDcC46E6K4u+lfqx4MT/BHLRSQmXeOaBfAy9i+yxfSR+5RsK5KFGPC+1CDPVpnkEMgFI/eU7qIwH34x8esiTw76ABBT7sicOKPnL26qN+6buI4AjCoP117FYJjaXJH5IgEY2TZ0CYVCBHTJ/NxHd9iHkNs+PdjVJt0gBZihJWKOFolae/7af52AL0fCX26eqmkgd2wraAQkmBWTIbjWGOjK7YZK5PVmZGpb1Bw3F5SjT6Jv5/5gQoRPtARAlTxSzHcjrPyv3mS5fpk85y0yz2UYAR5Hk7w2urGvwMNMHz5/duATsz/CbLXFGtp0ObnGIlKfZGWu0RnxLpsExMhOLY=");

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

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        Map<String, String> param = new HashMap<String, String>();

//        param.put("uuid", "52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE");
//        param.put("wm_logintoken", "");
//        param.put("request_id", "49DAE257-3234-40FF-9ED5-CAABD2DF5E54");
//        param.put("poilist_mt_cityid", "1");
//        param.put("wm_actual_longitude", "116597192");
//        param.put("wm_actual_latitude", "39924267");
//        param.put("req_time", "1503566564232");
//        param.put("trace_tag", "{\"action\":\"click\",\"src_page\":\"p_category\",\"src_block\":\"b_poilist\",\"src_item_id\":\"991466\",\"src_item_index\":\"0\",\"tgt_page\":\"p_poi\",\"req_time\":\"1503566564109\",\"extra\":\"{\\\"friend_comment\\\":\\\"2\\\",\\\"ad\\\":\\\"\\\"}\",\"tgt_block\":\"[\\\"b_shoppingcart\\\", \\\"b_increase\\\", \\\"b_decrease\\\", \\\"b_food_area\\\"]\"}");
//        param.put("wm_did", "862963034145140");
//        param.put("userid", "0");
//        param.put("wm_longitude", "116597192");
//        param.put("wm_channel", "1011");
//        param.put("wm_seq", "77");
//        param.put("poilist_wm_cityid", "110100");
//        param.put("version", "5.9.5");
//        param.put("push_token", "dpshfb89a695de47657830aa6f906a556b44atpu");
//        param.put("wm_appversion", "5.9.5");
//        param.put("wm_latitude", "39924267");
//        param.put("wm_mac", "02:00:00:00:00:00");
//        param.put("waimai_sign", "PVr/RWlWWEzXvW9Qtoh65H3gGCQ5CALfg+GsSQ8Rqm8Gtsg3/bJ2HTjs20bgBQPvJR4EQS5+w5m3O6HoFN3z2qODhwrFcRwkgkx5xLAZdXplgm+8P49zHSwNeSlvm3DWIU3o54m/kgyK+4aBPnIiOYY36rJ/xQZ8lWK3TI+ZK/k=");
//        param.put("wm_ctype", "android");
//        param.put("trace_id", "408");
//        param.put("app", "4");
//        param.put("wm_visitid", "d0920016-71e6-44d1-8641-2271ab724022");
//        param.put("platform", "4");
//        param.put("seq_id", "410");
//        param.put("wm_dversion", "23_6.0");
//        param.put("wm_uuid", "52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE");
//        param.put("wm_dtype", "Redmi Note 4");
//        param.put("page_index", "0");
//        param.put("wm_poi_id", "991466");
//        param.put("partner", "4");

//        HttpUtil.sendHttpPost("http://wmapi.meituan.com/api/v8/poi/food?utm_medium=android&utm_content=862963034145140&utm_term=50905&utm_source=1011&ci=1&utm_campaign=AwaimaiBwaimaiGhomepage_category_0_910_1690077__g_poilist_0_991466&uuid=52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1503566561971&__skua=d41d8cd98f00b204e9800998ecf8427e&__skno=b8a28ea4-9836-4669-924a-5a4cc37a1215&__skcy=QBb0k9etVoT8pD9BqNTfqB1OVsw%3D", null, param, null, "utf-8");

        param.put("wm_logintoken", "74r0eY27ct5Xig6W6wSookVISgUAAAAAmgQAAJ61n6rytcOegH4Lt_hJTNOt_C2jEn_ifme-SAMDvWx0Oc5fZddlUf2R8ijaDHYgtA");
        param.put("poilist_mt_cityid", "76");
        param.put("request_id", "B2FF7502-6FDD-4567-A4B0-FE92970FE294");
        param.put("uuid", "D8A9C3650864FF60CFFB282DD1540A13C4D1A8B40AEABF3055478C5726F177FE");
        param.put("wm_actual_longitude", "116597287");
        param.put("wm_actual_latitude", "39924250");
        param.put("req_time", "1505295019631");
        param.put("last_wm_poi_id", "0");
        param.put("trace_tag", "{\"action\":\"click\",\"src_page\":\"p_homepage\",\"src_block\":\"b_category\",\"src_item_id\":\"910\",\"src_item_index\":\"0\",\"tgt_page\":\"p_category\",\"req_time\":\"1505295019699\",\"extra\":\"{\\\"navigate_type\\\":910,\\\"category_code\\\":910,\\\"sub_category_code\\\":0,\\\"sort_code\\\":0}\",\"tgt_block\":\"[\\\"b_poilist\\\"]\"}");
        param.put("userid", "107784823");
        param.put("wm_did", "866052032544417");
        param.put("wm_longitude", "114719829");
        param.put("wm_channel", "1046");
        param.put("wm_seq", "60");
        param.put("poilist_wm_cityid", "130100");
        param.put("version", "5.11.3");
        param.put("sort_type", "0");
        param.put("page_size", "20");
        param.put("push_token", "dpshe5e92fcdfa9982fc8796f2b7d774f25catpu");
        param.put("load_type", "1");
        param.put("category_type", "910");
        param.put("navigate_type", "910");
        param.put("wm_appversion", "5.11.3");
        param.put("wm_latitude", "38344810");
        param.put("wm_mac", "02:00:00:00:00:00");
        param.put("waimai_sign", "SIvM7xpsh9bYizqQbBkxqOkz4UPq+HBNWAdcOaT5Ycn8hF7+XxDdzR8Ty7I1prItSh7jKVesyO2bss/F22ZWK6dMLnH7ZcpnZyASzznCi39ocmyBwfCFs1y0V2So3l3FFlfqh36CwD7Ld/p66iBfxDHv+jSKrh/yEmTT0QV56h0=");
        param.put("preload", "0");
        param.put("longitude", "114719829");
        param.put("rank_trace_id", "");
        param.put("wm_ctype", "android");
        param.put("app", "4");
        param.put("second_category_type", "0");
        param.put("wm_visitid", "4d2c20c7-ba54-4f17-8db1-81432dfefc14");
        param.put("platform", "4");
        param.put("seq_id", "116");
        param.put("wm_dversion", "24_7.0");
        param.put("wm_uuid", "D8A9C3650864FF60CFFB282DD1540A13C4D1A8B40AEABF3055478C5726F177FE");
        param.put("wm_dtype", "VTR-AL00");
        param.put("page_index", "0");
        param.put("latitude", "38344810");
        param.put("filter_type", "0");
        param.put("partner", "4");
        String menuResponse = HttpUtil.sendHttpPost("http://wmapi.meituan.com/api/v7/poi/channelpage?utm_medium=android&utm_content=866052032544417&utm_source=1046&utm_term=51103&ci=1&utm_campaign=AwaimaiBwaimaiGhomepage_category_0_910_1715254&uuid=D8A9C3650864FF60CFFB282DD1540A13C4D1A8B40AEABF3055478C5726F177FE&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1505295019186&__skua=d41d8cd98f00b204e9800998ecf8427e&__skno=ac274446-c382-4fb0-90f9-2aef13dabf21&__skcy=blje3EWZaG8OLaUVImJK7dozOmQ%3D", null, param, null, "utf-8");
        JSONObject jsonObject = JSON.parseObject(menuResponse);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("poilist");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonStore = jsonArray.getJSONObject(i);
            Store store = new Store();
            store.setName(jsonStore.getString("name"));
            store.setMonthSaleCount(jsonStore.getString("month_sales_tip"));
            store.setDistance(jsonStore.getString("distance"));
            store.setDeliveryTime(jsonStore.getString("delivery_time_tip"));
            store.setDeliverFee(jsonStore.getString("shipping_fee_tip"));
            store.setMinDeliverFee(jsonStore.getString("min_price_tip"));
            store.setAvgPrice(jsonStore.getString("average_price_tip"));
            JSONArray storeDiscountArray = jsonStore.getJSONArray("discounts2");
            for (int j = 0; j < storeDiscountArray.size(); j++) {
                JSONObject discount = storeDiscountArray.getJSONObject(j);
                store.setDiscount(store.getDiscount() + discount.getString("info"));
            }

            System.out.println(store);
            param.clear();
            param.put("uuid", "52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE");
            param.put("wm_logintoken", "");
            param.put("request_id", "49DAE257-3234-40FF-9ED5-CAABD2DF5E54");
            param.put("poilist_mt_cityid", "1");
            param.put("wm_actual_longitude", "116597192");
            param.put("wm_actual_latitude", "39924267");
            param.put("trace_tag", "{\"action\":\"click\",\"src_page\":\"p_category\",\"src_block\":\"b_poilist\",\"src_item_id\":\"" + jsonStore.getString("id") + "\",\"src_item_index\":\"0\",\"tgt_page\":\"p_poi\",\"req_time\":\"1503566564109\",\"extra\":\"{\\\"friend_comment\\\":\\\"2\\\",\\\"ad\\\":\\\"\\\"}\",\"tgt_block\":\"[\\\"b_shoppingcart\\\", \\\"b_increase\\\", \\\"b_decrease\\\", \\\"b_food_area\\\"]\"}");
            param.put("wm_did", "862963034145140");
            param.put("userid", "0");
            param.put("wm_longitude", "116597192");
            param.put("wm_channel", "1011");
            param.put("poilist_wm_cityid", "110100");
            param.put("version", "5.9.5");
            param.put("push_token", "dpshfb89a695de47657830aa6f906a556b44atpu");
            param.put("wm_appversion", "5.9.5");
            param.put("wm_latitude", "39924267");
            param.put("wm_mac", "02:00:00:00:00:00");
            param.put("wm_ctype", "android");
            param.put("trace_id", "408");
            param.put("app", "4");
            param.put("wm_visitid", "d0920016-71e6-44d1-8641-2271ab724022");
            param.put("platform", "4");
            param.put("seq_id", "410");
            param.put("wm_dversion", "23_6.0");
            param.put("wm_uuid", "52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE");
            param.put("wm_dtype", "Redmi Note 4");
            param.put("page_index", "0");
            param.put("wm_poi_id", "991466");
            param.put("partner", "4");
            BigInteger localBigInteger1 = new BigInteger("AC14E4A51F1B8E11A95971CA4EBD7E2314631F137596A66A43FA2D792B2FD8447CBD6553D591F00A8B9D58E8BA33C229317A0D122C965D84A286114A963C3AE2694C81665D5AF04C80A71CBF350CD4C66280DC8FADBE6B8EDA7B2EC3D0C50E150850445EF84D3A4192662AC135D912C2CA2C68176D79EC64CACFF34089482B69", 16);
            BigInteger localBigInteger2 = new BigInteger("010001", 16);
            RSAPublicKey localRSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(localBigInteger1, localBigInteger2));
            Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            localCipher.init(1, localRSAPublicKey);
            byte[] arrayOfByte1 = param.toString().getBytes();
            int j = arrayOfByte1.length;
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            int k = 0;
            if (j - i > 0) {
                if (j - i > 117) {
                }
                for (byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte1, i, 117); ; arrayOfByte3 = localCipher.doFinal(arrayOfByte1, i, j - i)) {
                    localByteArrayOutputStream.write(arrayOfByte3, 0, arrayOfByte3.length);
                    int m = k + 1;
                    i = m * 117;
                    k = m;
                    break;
                }
            }
            byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
            localByteArrayOutputStream.close();
            String str = new String(Base64Utils.encode(arrayOfByte2),"ASCII");
            System.out.println(str);
            param.put("req_time", "1503566564232");
            param.put("wm_seq", "77");

            param.put("waimai_sign", str);
            HttpUtil.sendHttpPost("http://wmapi.meituan.com/api/v8/poi/food?utm_medium=android&utm_content=862963034145140&utm_term=50905&utm_source=1011&ci=1&utm_campaign=AwaimaiBwaimaiGhomepage_category_0_910_1690077__g_poilist_0_991466&uuid=52322AC9917F651089E9C1AE2BA845BC2940D8E5392A118A6E9EA6E873A38ACE&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1503566561971&__skua=d41d8cd98f00b204e9800998ecf8427e&__skno=b8a28ea4-9836-4669-924a-5a4cc37a1215&__skcy=QBb0k9etVoT8pD9BqNTfqB1OVsw%3D", null, param, null, "utf-8");


        }
    }

}

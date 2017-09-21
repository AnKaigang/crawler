package cn.akgang.service;

import cn.akgang.dao.ParamMapper;
import cn.akgang.dao.RequestJobMapper;
import cn.akgang.dao.RequestQueueMapper;
import cn.akgang.entity.*;
import cn.akgang.util.HttpUtil;
import cn.akgang.util.MemCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akgang on 2017/7/20.
 */
@Service
public class CrawlService {

    @Autowired
    private HeaderService headerService;

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private RequestQueueMapper requestQueueMapper;

    @Autowired
    private RequestJobMapper requestJobMapper;

    public void crawl(Long jobId) throws Exception {
        List<RequestQueue> queueList = getAllQueueByJob(jobId);
        for (RequestQueue item : queueList) {
            Map<String, String> header = new HashMap<String, String>();
            if (item.getHeaderId() != null) {
                List<Header> headerList = headerService.getHeaderByKeyId(item.getHeaderId());
                for (Header h : headerList) {
                    header.put(h.getKey(), h.getValue());
                }
            }
            try {


                String result = HttpUtil.sendHttpGet(item.getUrl(), header,  "utf-8");
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public List<RequestJob> getAllJob() {
//        List<RequestJob> allJob = (List<RequestJob>) MemCacheUtil.get("job-list");
        List<RequestJob> allJob = null;
        if (allJob == null) {
            allJob = requestJobMapper.selectByExample(new RequestJobExample());
        }
        MemCacheUtil.set("job-list", allJob);
        return allJob;
    }

    public List<RequestQueue> getAllQueueByJob(Long jobId) {
        List<RequestQueue> queueList = null;
        //(List<RequestQueue>) MemCacheUtil.get("queue-list-jonId-" + jobId);
        if (queueList == null) {
            RequestQueueExample example = new RequestQueueExample();
            RequestQueueExample.Criteria requestQueueExampleCriteria = example.createCriteria();
            requestQueueExampleCriteria.andRequestIdEqualTo(jobId);
            example.setOrderByClause("`sort_num` asc");
            queueList = requestQueueMapper.selectByExample(example);
            MemCacheUtil.set("queue-list-jonId-" + jobId, queueList);
            return queueList;
        } else {
            return queueList;
        }
    }

    public static void main(String[] args) throws Exception {
        BigInteger localBigInteger1 = new BigInteger("AC14E4A51F1B8E11A95971CA4EBD7E2314631F137596A66A43FA2D792B2FD8447CBD6553D591F00A8B9D58E8BA33C229317A0D122C965D84A286114A963C3AE2694C81665D5AF04C80A71CBF350CD4C66280DC8FADBE6B8EDA7B2EC3D0C50E150850445EF84D3A4192662AC135D912C2CA2C68176D79EC64CACFF34089482B69", 16);
        BigInteger localBigInteger2 = new BigInteger("010001", 16);

        RSAPublicKey localRSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(localBigInteger1, localBigInteger2));
        Cipher localCipher = Cipher.getInstance("RSA");
        localCipher.init(Cipher.ENCRYPT_MODE, localRSAPublicKey);
        byte[] arrayOfByte1 = "/api/v8/poi/food/C9E9F92555682D2CC3A87759B6C0A50F12D66F83D02B69C72BEC16F31A4219E6/1505983987165/44".toString().getBytes();
        int j = arrayOfByte1.length;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        int ii = 0;
        int k = 0;
        if (j - ii > 0) {
            if ((j - ii) < 117) {
                byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte1, ii, arrayOfByte1.length);
                localByteArrayOutputStream.write(arrayOfByte3, 0, arrayOfByte3.length);
            } else {
                int length = 0;
                for (byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte1, ii, 117); ; arrayOfByte3 = localCipher.doFinal(arrayOfByte1, ii, length)) {
                    localByteArrayOutputStream.write(arrayOfByte3, 0, arrayOfByte3.length);
                    int m = k + 1;
                    ii = m * 117;
                    k = m;
                    if (j - ii > 117) {
                        length = 117;
                    } else {
                        length = j - ii;
                    }
                }
            }

        }
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.close();
        String str = new String(Base64Utils.encode(arrayOfByte2), "ASCII");
        System.out.println(str);
    }
}

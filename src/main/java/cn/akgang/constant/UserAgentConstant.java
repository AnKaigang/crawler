package cn.akgang.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by akgang on 2017/9/27.
 */
public class UserAgentConstant {
    public static final List<String> uaList = new LinkedList<String>();

    static {
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
    }

    public static String getRandomUA(){
        int uaIndex = (int) (Math.random() * uaList.size());
        return uaList.get(uaIndex);
    }
}

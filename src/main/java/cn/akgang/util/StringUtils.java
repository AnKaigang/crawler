package cn.akgang.util;

/**
 * Created by akgang on 2017/7/10.
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


    public static boolean isNotEmpty(String s) {
        return s != null && s.length() != 0;
    }
}

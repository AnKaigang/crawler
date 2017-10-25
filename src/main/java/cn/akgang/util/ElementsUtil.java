package cn.akgang.util;


import org.jsoup.select.Elements;

/**
 * @author akgang
 * @date 2017/10/18
 */
public class ElementsUtil {
    public static boolean isEmpty(Elements elements) {
        return elements == null || elements.size() == 0 ? true : false;
    }
}

import cn.akgang.util.MemCacheUtil;
import com.sun.javaws.CacheUtil;
import com.whalin.MemCached.MemCachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by akgang on 2017/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource.xml", "classpath:applicationContext.xml"})
public class CacheTest {

    @Test
    public void test1() {
        MemCacheUtil.set("key","11111111111",60);
        Object o = MemCacheUtil.get("key");
    }
}

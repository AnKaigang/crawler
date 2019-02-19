package cn.akgang.util;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Calendar;
import java.util.Date;

/**
 * @author akgang
 * @date 2017/7/20
 */
public class MemCacheUtil {
    private static MemCachedClient client = null;

    static {
        //memcached服务器
        String[] servers = {"127.0.0.1:11211"};
        /**
         * 设置连接池可用cache服务器的权重，和server数组的位置一一对应
         */
        Integer[] weights = {1};
        /**
         * 这个类用来创建管理客户端和服务器通讯连接池，客户端主要的工作包括数据通讯、服务器定位、hash码生成等都是由这个类完成的。
         * 获得连接池的单态方法。这个方法有一个重载方法getInstance( String poolName )，每个poolName只构造一个SockIOPool实例。
         * 缺省构造的poolName是default。
         */
        SockIOPool pool = SockIOPool.getInstance();
        //设置memcached服务器
        pool.setServers(servers);
        //设置memcached服务器权重
        pool.setWeights(weights);
        //设置容错开关设置为TRUE，当当前socket不可用时，程序会自动查找可用连接并返回，否则返回NULL，默认状态是true，建议保持默认
        pool.setFailover(true);
        //设置开始时每个cache服务器的可用连接数
        pool.setInitConn(10);
        //设置每个服务器最少可用连接数
        pool.setMinConn(5);
        //设置每个服务器最大可用连接数
        pool.setMaxConn(250);
        /**
         * 设置连接池维护线程的睡眠时间
         * 设置为0，维护线程不启动
         * 维护线程主要通过log输出socket的运行状况，监测连接数目及空闲等待时间等参数以控制连接创建和关闭。
         */
        pool.setMaintSleep(30);
        /**
         * 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
         */
        pool.setNagle(false);
        /**
         * 设置socket的读取等待超时值
         */
        pool.setSocketTO(3000);
        /**
         * 设置连接心跳监测开关。
         * 设为true则每次通信都要进行连接是否有效的监测，造成通信次数倍增，加大网络负载，因此该参数应该在对HA要求比较高的场合设为TRUE，默认状态是false。
         */
        pool.setAliveCheck(true);
        /**
         * 设置完pool参数后最后调用该方法，启动pool。
         */
        pool.initialize();

        /**
         * 创建一个memcached客户端，所有对memcached中数据操作的方法都在这个类里面
         */
        client = new MemCachedClient();
    }

    public static Object get(String key) {
        return client.get(key);
    }

    public static Object set(String key, Object value, Integer seconds) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);
        return client.set(key, value, calendar.getTime());
    }

    public static Object set(String key, Object value) {
        return set(key, value, 60 * 60);
    }
}

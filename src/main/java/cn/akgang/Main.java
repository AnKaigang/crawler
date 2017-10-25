package cn.akgang;

import cn.akgang.task.MeiTuanWaiMaiTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by akgang on 2017/9/29.
 */
public class Main {
    public static ApplicationContext context;

    private static Logger logger= LoggerFactory.getLogger(Main.class);

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        ITask task = (ITask) context.getBean(args[0]);
        logger.info("{}开始执行",args[0]);
        task.execute();
    }
}

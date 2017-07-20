package cn.akgang;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.util.log.StdErrLog;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by akgang on 2017/7/10.
 */
public class crawler {
    public static void main(String[] args) throws Exception {
        // 设置Jetty日志
        System.setProperty("org.eclipse.jetty.util.log.class", StdErrLog.class.getName());

        HandlerCollection handlers = new HandlerCollection();

        Server server = new Server(Integer.valueOf(args[0]));

        RequestLogHandler requestLogHandler = new RequestLogHandler();

        // 设置context
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        String war = "src/main/webapp";
        webapp.setWar(war);

        // PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
        webapp.setParentLoaderPriority(true);

        handlers.setHandlers(new Handler[]{webapp, new DefaultHandler(), requestLogHandler});
        server.setHandler(handlers);


        server.start();
    }
}

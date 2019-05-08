package xihe.ji.pet_springboot.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import xihe.ji.pet_springboot.server.NettyServer;

/**
 * @author : jch
 * @version V1.0
 * @Project: jch_netty
 * @Package xihe.ji.pet_springboot.util
 * @Description: 线程池
 * @date Date : 2018年09月20日 08:52
 */
public class MyThread {
    public  static ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
    static {
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(200);
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(5);
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(1000);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.initialize();
    }
    public static void start(){
        poolTaskExecutor.execute(new myThreadS());
    }
    public static void shutdown(){
        poolTaskExecutor.shutdown();
    }
    static class myThreadS implements Runnable{
        @Override
        public void run() {
            new NettyServer().init();
        }
    }
}

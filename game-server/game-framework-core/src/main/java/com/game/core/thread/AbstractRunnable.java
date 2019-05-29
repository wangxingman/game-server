package com.game.core.thread;

import com.game.core.ws.dto.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: wx
 * @Desc :  线程池工具类
 * @Date : 下午 6:48 2019/5/7 0007
 */
@Slf4j
public abstract class AbstractRunnable implements Runnable {

    protected static Map<Long, MessageType> map = new ConcurrentHashMap();

    protected static Long identification;

    protected String str;
    
    private static ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();

    /**延时线程*/
    private ScheduledExecutorService threadPool = new ScheduledThreadPoolExecutor(1);

    /**延时线程返回值*/
    private ScheduledFuture<?> timeoutFuture;

    //静态代码块紧执行一次
     static {
         log.info("线程实例化！");
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

    public static MessageType getMessageType() {
        return map.get(identification);
    }


    /**
     * @Author: wx
     * @Desc  : 运行spring线程 无返回值   执行这个的时候才会执行run方法
     * @Date  : 下午 8:16 2019/5/7 0007
     * @params:
     */
    public void startThread() {
        poolTaskExecutor.execute(this);
    }

    /**
     * @Author: wx
     * @Desc  : 运行spring线程 有返回值
     * @Date  : 下午 8:28 2019/5/7 0007
     * @params:
     */
    public void startThreadReturn() {
        poolTaskExecutor.submitListenable(this);
    }

    /**
     * @Author: wx
     * @Desc  :  关闭线程
     * @Date  : 下午 8:17 2019/5/7 0007
     * @params:
     */
    public void stopThread() {
        poolTaskExecutor.shutdown();
    }
    
    /**
     * @Author: wx
     * @Desc  : 各种类型学习
     * @Date  : 下午 8:20 2019/5/7 0007
     * @params:
     */
    public void countThread() {
        log.info("poolTaskExecutor.getPoolSize()"+ poolTaskExecutor.getPoolSize());
        log.info("poolTaskExecutor.getActiveCount()"+ poolTaskExecutor.getActiveCount());
        log.info("poolTaskExecutor.getCorePoolSize()"+ poolTaskExecutor.getCorePoolSize());
        log.info("poolTaskExecutor.getKeepAliveSeconds()"+ poolTaskExecutor.getKeepAliveSeconds());
        log.info("poolTaskExecutor.getThreadPoolExecutor()"+ poolTaskExecutor.getThreadPoolExecutor());
        log.info("poolTaskExecutor.getMaxPoolSize()"+ poolTaskExecutor.getMaxPoolSize());
    }

    /**
     * @Author: wx
     * @Desc  : 关掉延时线程
     * @Date  : 下午 8:24 2019/5/7 0007 
     * @params: 
     */
    protected void stopTimeoutThread() {
        if (timeoutFuture != null && !timeoutFuture.isCancelled()) {
            timeoutFuture.cancel(true);
        }
    }

    /**
     * @Author: wx
     * @Desc  : 隔段时间继续运行
     * @Date  : 下午 8:24 2019/5/7 0007
     * @params:
     */
    protected void startTimeoutThread(int timeout) {
        this.timeoutFuture = threadPool.schedule(this, timeout, TimeUnit.SECONDS);
    }

    /**
     * @Author: wx
     * @Desc  : 隔段时间继续运行
     * @Date  : 下午 8:24 2019/5/7 0007
     * @params:
     */
    protected void startTimeoutMilliThread(int timeout) {
        this.timeoutFuture = threadPool.schedule(this, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 隔段时间继续运行
     *
     * @param start
     *            多长时间开始第一次执行
     * @param interval
     *            间隔多长时间
     */
    protected void startTimeoutThread(int start, int interval) {
        this.timeoutFuture = threadPool.scheduleAtFixedRate(this, start, interval, TimeUnit.SECONDS);
    }

    /**
     * @param start
     *            多长时间开始第一次执行
     * @param interval
     *            间隔多长时间
     */
    protected void startTimeoutMilliThread(int start, int interval) {
        this.timeoutFuture = threadPool.scheduleAtFixedRate(this, start, interval, TimeUnit.MILLISECONDS);
    }

}

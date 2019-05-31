package com.game.core.ws.initializer;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 12:21 2019/5/17 0017
 * @explain : 初始化 走run
 */
public class WsApplicationRunner implements ApplicationRunner, DisposableBean {

    private final ApplicationContext applicationContext;

    private List<WsInitializer> initializers;

    @Autowired
    public WsApplicationRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @Author: wx
     * @Date  : 下午 12:27 2019/5/17 0017 
     * @params: 
     * @Desc  :  执行完销毁
     */
    @Override
    public void destroy() throws Exception {
        for (WsInitializer wsInitializer : initializers) {
            wsInitializer.destroy();
        }
    }

    /**
     * @Author: wx
     * @Date  : 下午 12:21 2019/5/17 0017 
     * @params: 
     * @Desc  :  创建这个方法的时例的时候 会执行这个方法
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //获取所有调用这个方法接口
        Map<String, WsInitializer> runnerMap = applicationContext.getBeansOfType(WsInitializer.class);
        //java 1.8 排序
        List<WsInitializer> initializers
                = runnerMap.values().stream().sorted(Comparator.comparing(WsInitializer::order)).collect(Collectors.toList());
        for (WsInitializer wsInitializer : initializers) {
            //运行netty的服务器 初始化的时候 建立连接服务器
            wsInitializer.init();
        }
    }
}

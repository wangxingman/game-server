package com.game.core.ws.initializer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:37 2019/5/17 0017
 * @explain :   初始化netty服务
 */
public class HallConfig {

    /**
     * @Author: wx
     * @Date  : 下午 4:38 2019/5/17 0017 
     * @params: 
     * @Desc  : 初始化
     */
    @Bean
    public WsApplicationRunner wsApplicationRunner(ApplicationContext applicationContext) {
        return new WsApplicationRunner(applicationContext);
    }
}

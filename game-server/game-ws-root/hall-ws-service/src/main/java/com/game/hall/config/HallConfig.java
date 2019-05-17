package com.game.hall.config;

import com.game.core.ws.initializer.WsApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:37 2019/5/17 0017
 * @explain :
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

package com.game.hall;

import com.game.core.utils.ManagerBanaderUtil;
import com.game.core.annotation.EnableNetty;
import com.game.hall.po.HallProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/5 0005
 */

@EnableNetty
@SpringBootApplication
@EnableConfigurationProperties(value = HallProperties.class)
public class HallWsServiceApplication {
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(HallWsServiceApplication.class);
        springApplication.setBanner(new ManagerBanaderUtil());
        springApplication.run( args);
    }
}
    
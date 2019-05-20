package com.game.gateway;

import com.game.core.utils.ManagerBanaderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/20 0005
 */

@SpringBootApplication
public class GateWayServiceApplication {
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GateWayServiceApplication.class);
        springApplication.setBanner(new ManagerBanaderUtil());
        springApplication.run( args);
    }
}
    
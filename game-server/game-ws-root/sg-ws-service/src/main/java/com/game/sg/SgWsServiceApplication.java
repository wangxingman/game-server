package com.game.sg;

import com.game.common.dto.NettyParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/5 0005
 */
@SpringBootApplication
@EnableConfigurationProperties(value = NettyParams.class)
public class SgWsServiceApplication {
    
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication.run(SgWsServiceApplication.class, args);
    }
}
    
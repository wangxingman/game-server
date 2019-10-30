package com.game.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wx
 * @Desc:
 * @Date: 下午 3:56 2019/5/5 0005
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//该注解未能排除sql的自动注册
public class LogServerApplication {
    
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 3:57 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication.run(LogServerApplication.class, args);
    }

}

package com.game.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:26 2019/5/15 0015
 * @explain :
 */
@SpringBootApplication
@EnableDiscoveryClient // 注解能激活Eureka中的DiscoveryClient实现
@EnableConfigServer // 注解，开启Config Server dsa
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}

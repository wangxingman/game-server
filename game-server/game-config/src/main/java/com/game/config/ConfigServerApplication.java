package com.game.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:26 2019/5/15 0015
 * @explain :
 */
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@EnableDiscoveryClient 
@EnableConfigServer 
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}

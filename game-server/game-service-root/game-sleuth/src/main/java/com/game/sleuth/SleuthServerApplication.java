package com.game.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:49 2019/6/17 0017
 * @explain :
 */
@EnableZipkinServer
/**开启zipkin可视化*/
@SpringBootApplication
@EnableDiscoveryClient
public class SleuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SleuthServerApplication.class);
    }
}

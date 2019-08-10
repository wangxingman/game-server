package com.game.see;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:38 2019/5/11 0011
 * @explain :                                                       
 */
@EnableScheduling
@SpringBootApplication()
@EnableEurekaClient
public class SeeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeeServerApplication.class);
    }
}

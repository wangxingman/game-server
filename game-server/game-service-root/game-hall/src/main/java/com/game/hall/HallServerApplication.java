package com.game.hall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:38 2019/5/11 0011
 * @explain :                                                       
 */
@SpringBootApplication
@EnableEurekaClient
public class HallServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HallServerApplication.class);
    }
}

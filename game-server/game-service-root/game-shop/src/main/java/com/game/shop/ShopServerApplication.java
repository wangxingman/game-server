package com.game.shop;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:38 2019/5/11 0011
 * @explain :                                                       
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction //开启lcn
public class ShopServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopServerApplication.class);
    }
}

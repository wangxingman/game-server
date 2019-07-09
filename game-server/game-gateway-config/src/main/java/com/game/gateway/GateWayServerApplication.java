package com.game.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/20 0005
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication(exclude = { RabbitAutoConfiguration.class})
public class GateWayServerApplication {

    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayServerApplication.class);
    }


}
    
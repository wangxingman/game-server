package com.game.auth;

import org.springframework.boot.SpringApplication;      
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:38 2019/5/11 0011
 * @explain :
 */
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication()
@EnableCaching
@EntityScan("com.game.common.entity.*")          //扫描实体类
/**开启lcn分布式事务*/
//@EnableDistributedTransaction
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class);
    }
}

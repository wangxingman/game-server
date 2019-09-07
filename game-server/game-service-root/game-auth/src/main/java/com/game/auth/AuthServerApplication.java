package com.game.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:38 2019/5/11 0011
 * @explain :
 */
@RestController
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication()
@EnableCaching
@EntityScan("com.game.common.entity.*")          //扫描实体类
/**开启lcn分布式事务*/
//@EnableDistributedTransaction
public class AuthServerApplication {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class);
    }
    
    @GetMapping("/getExample")
    public void example() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String monthFormat = String.format("%1$02d", month + 1);
        Long increment = stringRedisTemplate.opsForValue().increment(monthFormat);
        String.format("%1$06d", increment);
        //使用这个方式 可将重复的唯一id 将其不重复话
        System.out.println("increment"+increment);
    }

}

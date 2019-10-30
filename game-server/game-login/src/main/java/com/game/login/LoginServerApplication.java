package com.game.login;

import com.game.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Autheor: wx
 * @Desc :
 * @Date : 下午 5:29 2019/5/6 0006
 * 排除自动注入的问题
 */
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
public class LoginServerApplication {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        new RedisUtil(stringRedisTemplate);
        return new BCryptPasswordEncoder();
    }

}

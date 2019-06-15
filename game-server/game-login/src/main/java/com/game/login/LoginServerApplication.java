package com.game.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Autheor: wx
 * @Desc :
 * @Date : 下午 5:29 2019/5/6 0006
 *          排除自动注入的问题
 */
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
public class LoginServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class);
    }
}

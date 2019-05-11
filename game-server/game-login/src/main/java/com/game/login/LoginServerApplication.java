package com.game.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 5:29 2019/5/6 0006
 */
@RestController
@EnableSwagger2
@SpringBootApplication
public class LoginServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class);
    }
}

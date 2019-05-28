package com.game.config.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 6:56 2019/5/28 0028
 * @explain :
 */
@Configuration
public class ExampleConfigurtion {

    @Bean
    public void test() {
        System.out.println("---------------");
    }
}

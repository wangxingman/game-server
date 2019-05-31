package com.game.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: wx
 * @Date  : 下午 5:23 2019/5/30 0030 
 * @params: 
 * @Desc  :  生成jpa 只能在当前项目模块
 */
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class);
    }
}

package com.game.auth.annotation;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:04 2019/6/20 0020
 * @explain :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan(basePackages = "com.game.auth.*")            //扫描service controller
@EnableJpaRepositories(basePackages = "com.game.auth.*")      //扫描dao
@EntityScan("com.game.common.entity.user.*")          //扫描实体类
public @interface AuthEntityAnnotation {
    
}

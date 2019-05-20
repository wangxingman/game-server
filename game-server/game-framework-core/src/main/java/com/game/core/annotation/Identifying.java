package com.game.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:59 2019/5/20 0020
 * @explain : 自定义注解标识服
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Identifying {
}

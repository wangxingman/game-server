package com.game.core.annotation;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:54 2019/5/20 0020
 * @explain : 自定义注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMessage {
    LogType type() default LogType.HALL_JOIN;

    String value() default "";

    boolean check() default true;

    enum LogType {
        //加入房间
        HALL_JOIN("加入房间");

        @Getter
        private final  String key;

        LogType(String key) {
            this.key = key;
        }
    }
}

package com.game.core.annotation;

import com.game.core.ws.initializer.HallConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:41 2019/5/17 0017
 * @explain :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@Import(HallConfig.class)
public @interface EnableNetty {
}

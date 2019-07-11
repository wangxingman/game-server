package com.game.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:24 2019/7/11 0011
 * @explain :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidRepeatableCommit {

    /**
     * 指定时间内不可重复提交,单位毫秒
     * @return
     */
    long timeout()  default 30000 ;
    
}

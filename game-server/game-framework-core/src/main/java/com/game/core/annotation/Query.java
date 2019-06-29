package com.game.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:18 2019/6/29 0029
 * @explain :  定义模糊查询字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /** 基本对象的属性名 */
    String propName() default "";
    /**查询方式 */
    Type type() default Type.EQUAL;

    /**
     * 连接查询的属性名，如User类中的dept
     * @return
     */
    String joinName() default "";

    /**
     * 默认左连接
     * @return
     */
    Join join() default Join.LEFT;

    enum Type {
        EQUAL
        /**大于等于 */
        , GREATER_THAN
        /**小于等于 */
        , LESS_THAN
        /**中模糊查询 */
        , INNER_LIKE
        /**左模糊查询 */
        , LEFT_LIKE
        /**右模糊查询 */
        , RIGHT_LIKE
        /**小于 */
        , LESS_THAN_NQ
        //** jie 2019/6/4 包含 */
        , IN
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:21 2019/6/29 0029
     * @params:
     * @Desc  :  适用于简单连接查询，复杂的请自定义该注解，或者使用sql查询
     */
    enum Join {
        /** 左连接 */
        LEFT
        /** 右连接 */
        , RIGHT
    }

}

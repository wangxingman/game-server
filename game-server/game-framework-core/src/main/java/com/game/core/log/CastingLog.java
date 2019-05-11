package com.game.core.log;

import lombok.Data;
import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：
 * 创建人：@
 * 版本：version-01
 * DATA：2019/1/10
 * TIME：12:15
 * 注释：
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CastingLog {

    LogType type() default LogType.OTHER;

    String value() default "";

    enum LogType {
        /**
         * 新增
         */
        INSERT(1),
        /**
         * 修改
         */
        UPDATE(2),
        /**
         * 删除
         */
        DELETE(3),
        /**
         * 其它
         */
        OTHER(4);

        @Getter
        private final int key;

        LogType(int key) {
            this.key = key;
        }
    }


}

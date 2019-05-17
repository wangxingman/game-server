package com.game.common.comman.api;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 7:45 2019/5/6 0006
 */
@Data
public class Result {
    /**
     * 自定义状态码
     */
    private int code;

    /**
     * 是否成功返回
     */
    private boolean success;

    /**
     * 描述
     */
    private String message;

    /**
     * 内容
     */
    private Object data;

    /**
     * 下发时间
     */
    private Date timestamp = new Date();

    /**
     * 构造描述：
     */
    public Result(int code, boolean success, String message, Object data) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造描述：
     */
    public Result(int code, boolean success, String message) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
    }
    
}

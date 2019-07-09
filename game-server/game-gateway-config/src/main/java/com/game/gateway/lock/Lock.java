package com.game.gateway.lock;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:26 2019/7/9 0009
 * @explain : 全局锁，包括锁的名称
 */
public class Lock implements Serializable {

    private String name;
    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

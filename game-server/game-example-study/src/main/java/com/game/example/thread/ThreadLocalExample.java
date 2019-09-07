package com.game.example.thread;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:49 2019/8/10 0010
 * @explain :
 */
public class ThreadLocalExample {

    public interface Session {

    }

    public static class Example implements Session {

    }

    /**
     * @Author: wx
     * @Date : 下午 12:01 2019/8/10 0010
     * @params:
     * @Desc :   获取当前线程 初始化线程map
     */
    static ThreadLocal<Session> threadLocal = new ThreadLocal();

    public static void set() {
        threadLocal.set(new Example());
    }

    public static void main(String[] args) {
        ThreadLocalExample.set();
    }
}

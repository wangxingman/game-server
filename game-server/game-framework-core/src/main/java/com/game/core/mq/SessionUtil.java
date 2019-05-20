/*
 * 创建时间： 2017年07月31日
 *
 * @Copyright 武汉雷软科技有限公司
 */
package com.game.core.mq;

import com.game.common.exception.MessageException;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:25 2019/5/20 0020
 * @explain : 
 */
public class SessionUtil {

    public interface Session {

    }

    /**
     * SESSION_LOCAL 线程局部变量 处理当前用户
     */
    private static ThreadLocal<Session> SESSION_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前用户
     *
     * @param session
     */
    public static void set(Session session) {
        SESSION_LOCAL.set(session);
    }

    /**
     * 取得当前用户
     *
     * @return 当前用户
     */
    public static Session get() {
        Session currentSession = SESSION_LOCAL.get();
        if (currentSession == null) {
            throw new MessageException("服务器异常");
        }
        return currentSession;
    }

    /**
     * 销毁当前用户
     */
    public static void destory() {
        SESSION_LOCAL.remove();
    }
}

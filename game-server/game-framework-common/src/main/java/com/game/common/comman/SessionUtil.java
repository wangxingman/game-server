package com.game.common.comman;


/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:52 2019/7/23 0023
 * @explain :
 */
public class SessionUtil {

    public interface Session {

    }

    /**
     * @Author: wx
     * @Date  : 下午 2:52 2019/7/23 0023 
     * @params: 
     * @Desc  : SESSION_LOCAL 线程局部变量 处理当前用户
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
            throw new NullPointerException("服务器异常");
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

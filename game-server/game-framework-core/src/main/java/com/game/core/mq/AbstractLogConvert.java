package com.game.core.mq;

import com.game.core.annotation.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:27 2019/5/20 0020
 * @explain :
 */
public abstract class AbstractLogConvert {

    /**
     * 日志队列
     */
    @Autowired
    private LogQueneSend logQueneSend;

    /**
     * 发送消息
     *
     * @param logMessage 日志注解
     * @param args       方法参数
     * @param session    用户
     * @throws Throwable
     */
    @Async
    public void sendMessage(LogMessage logMessage, Object[] args, SessionUtil.Session session) throws Throwable {
        logQueneSend.send(formatMessage(logMessage, args, session));
    }

    /**
     * 格式化日志
     *
     * @param logMessage 日志注解
     * @param args       方法参数
     * @param session    用户
     * @return 格式后日志
     * @throws Throwable
     */
    public abstract Object formatMessage(LogMessage logMessage, Object[] args, SessionUtil.Session session)
            throws Throwable;
}

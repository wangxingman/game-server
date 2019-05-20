package com.game.core.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 10:29 2019/5/20 0020
 * @explain :
 */
public class LogQueneSend {

    /**
     * 日志队列
     */
    public static final String LOG_QUENE = "IM_FRAMEWORD_CORE_QUEUE";

    /**
     * 队列处理器
     */
    @Autowired
    public RabbitTemplate rabbitTemplate;

    /**
     * 发送日志
     *
     * @param message
     *            日志内容
     */
    public void send(Object message) {
        rabbitTemplate.convertAndSend(LOG_QUENE, message);
    }
}

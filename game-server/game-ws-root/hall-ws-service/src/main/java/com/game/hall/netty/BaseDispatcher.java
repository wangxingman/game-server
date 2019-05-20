package com.game.hall.netty;

import com.game.hall.netty.manager.Handler;
import com.game.hall.po.MessageType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:19 2019/5/17 0017
 * @explain :
 */
public interface BaseDispatcher {

    default void init(){ }

    Map<MessageType, Handler> localMap = new ConcurrentHashMap<>();

    static Handler getDispatch_copy(MessageType messageType) {
        return localMap.get(messageType);
    }

    static void register_copy(MessageType messageType, Handler logic) {
        localMap.put(messageType, logic);
    }
}

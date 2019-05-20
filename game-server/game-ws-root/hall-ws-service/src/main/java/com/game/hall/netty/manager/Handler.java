package com.game.hall.netty.manager;

import com.game.hall.po.NetMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:52 2019/5/5 0005
 */
@Slf4j
public abstract class Handler {

    public abstract void handle(WebSocket webSocket, byte[] bytes);

    public void handle(WebSocket webSocket, NetMessage request) {
        byte[] bytes = request.getMessageBody().getBytes();
        if(Objects.isNull(bytes)) {
            log.info("您传来的东西是null的！");
        }
        this.handle(webSocket, bytes);
    }
}

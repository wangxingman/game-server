package com.game.hall.hanlder;

import com.game.core.annotation.Identifying;
import com.game.core.annotation.LogMessage;
import com.game.hall.netty.manager.Handler;
import com.game.hall.netty.manager.WebSocket;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:47 2019/5/17 0017
 * @explain :
 */
@Slf4j
@Identifying
public class JoinHallHanlder extends Handler {

    @Override
    @LogMessage(type = LogMessage.LogType.HALL_JOIN ,value = "加入房间")
    public void handle(WebSocket webSocket, byte[] bytes) {
        //初始化 一些基本数据
        String s = new String(bytes);
        if(Objects.isNull(s)) {
            log.info("传输的值为null");
        }else {
            
        }
    }
    
}

package com.game.hall.hanlder;

import com.alibaba.fastjson.JSONObject;
import com.game.core.annotation.Identifying;
import com.game.core.annotation.LogMessage;
import com.game.core.ws.server.Manager.Handler;
import com.game.core.ws.server.Manager.WebSocket;
import com.game.hall.config.LocalSpringServiceManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:47 2019/5/17 0017
 * @explain : gateway进来初始化数据
 */
@Slf4j
@Identifying
public class JoinHallHanlder extends Handler {

    @Override
    @LogMessage(type = LogMessage.LogType.HALL_JOIN ,value = "gateway加入房间")
    public void handle(WebSocket webSocket, byte[] bytes) {
        //初始化 一些基本数据
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        if(Objects.isNull(jsonObject)) {
            log.info("传输的值为null");
        }else {
            LocalSpringServiceManager.getInstance().getGameService()
                    .gateWayJoin(webSocket,jsonObject);
        }
    }
}

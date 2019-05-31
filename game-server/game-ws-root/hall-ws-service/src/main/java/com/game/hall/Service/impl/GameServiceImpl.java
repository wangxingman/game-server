package com.game.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.core.ws.server.Manager.WebSocket;
import com.game.hall.Service.abs.GameServiceImplAbrstarct;
import com.game.hall.service.GameService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:47 2019/5/28 0028
 * @explain :
 */
@Component
public class GameServiceImpl extends GameServiceImplAbrstarct implements GameService {

    @Override
    public String clientJoin() {
        return null;
    }

    @Override
    public String gateWayJoin(WebSocket webSocket, JSONObject jsonObject) {
        // 玩家ID    【如何快速获取用户是否之前 入册过该游戏】
        //选择对应的服务器  就是对应的端口启动的 同样的服务
        System.out.println("------------------------------");
        return null;
    }
}

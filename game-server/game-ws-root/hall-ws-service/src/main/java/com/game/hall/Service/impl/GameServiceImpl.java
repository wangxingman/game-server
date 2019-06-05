package com.game.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.common.Const.Errors;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.core.ws.server.manager.WebSocket;
import com.game.hall.service.GameService;
import com.game.hall.service.abs.GameServiceImplAbrstarct;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

    /**
     * @Author: wx
     * @Date  : 下午 7:51 2019/6/5 0005
     * @params:
     * @Desc  : 验证游戏名
     */
    @Override
    public void addauAccount() {
        
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:24 2019/6/3 0003 
     * @params: 
     * @Desc  :   网关加入房间
     */
    @Override
    public String gateWayJoin(WebSocket webSocket, JSONObject jsonObject) {
        // 玩家ID    【如何快速获取用户是否之前 入册过该游戏】
        //选择对应的服务器  就是对应的端口启动的 同样的服务
        System.out.println("------------------------------");
        if(Objects.nonNull(webSocket.getUserId())) {

        } else {
            webSocket.send(NetMessage.builder().bytes(Errors.no_false_error.getMsg().getBytes()).messageType(new MessageType(Const.hall.JOIN_HALL)).build());
        }
        return "";
    }
}

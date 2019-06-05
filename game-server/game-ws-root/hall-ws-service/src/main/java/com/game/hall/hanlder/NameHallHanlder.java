package com.game.hall.hanlder;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.common.Const.Errors;
import com.game.core.annotation.Identifying;
import com.game.core.annotation.LogMessage;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.core.ws.server.manager.Handler;
import com.game.core.ws.server.manager.WebSocket;
import com.game.hall.config.name.AbsServer;
import com.game.hall.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:50 2019/6/5 0005
 * @explain :用户创建名字的操作
 *
 */
@Slf4j
@Identifying 
public class NameHallHanlder extends Handler {
   
    @Override
    @LogMessage(type = LogMessage.LogType.HALL_NAME ,value = "用户名字操作")
    public void handle(WebSocket webSocket, byte[] bytes) {
        //todo 好像 多个服务器 之间的名字 也不能重复 所以怎么获取数据 各种
       //判断用户是否
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        String UAccount = jsonObject.getString("UAccount");
        Map<Integer, String> map = AbsServer.getConcurrentHashMap();
        if(map.containsKey(webSocket.getUserId()) && map.containsValue(UAccount)) {
            webSocket.send(
                    NetMessage.builder()
                            .messageType(new MessageType(Const.hall.NAME_HALL_REP))
                            .bytes(Errors.name_three_are.getMsg().getBytes())
                            .build());
        } else {

            UserDto userDto = AbsServer.getConcurrentHashMapUserDto().get(webSocket.getUserId());
            userDto.setUAccount(UAccount);
            webSocket.send(
                    NetMessage.builder()
                            .messageType(new MessageType(Const.hall.NAME_HALL_REP))
                            .bytes(JSONObject.toJSONString(userDto).getBytes())
                            .build());
        }
    }
}

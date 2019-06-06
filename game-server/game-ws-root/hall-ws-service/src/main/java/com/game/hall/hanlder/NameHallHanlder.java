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
import java.util.Objects;

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
        String UAccount = jsonObject.getString("uAccount");
        int uId = jsonObject.getIntValue("uId");
        Map<Integer, String> map = AbsServer.getConcurrentHashMap();
        if(map.containsKey(uId) && map.containsValue(UAccount)) {
            log.info("用户的名字出现重复！");
            webSocket.send(
                    NetMessage.builder()
                            .messageType(new MessageType(Const.hall.NAME_HALL_REP))
                            .bytes(Errors.name_three_are.getMsg().getBytes())
                            .build());
        } else {
            log.info("用户添加名字！");
            //todo 用户游戏的判断  游戏结束的时候 将数据统一保存 或者说 在一定情况 异步保存
            if(UAccount.length()>100) {
                log.info("客户端输入的名字 不允许");
            }
            System.out.println("-------start------"+AbsServer.getConcurrentHashMapUserDto());
            UserDto userDto = AbsServer.getConcurrentHashMapUserDto().get(uId);
            userDto.setUAccount(UAccount);
            System.out.println("--------end-----"+AbsServer.getConcurrentHashMapUserDto());
            webSocket.send(
                    NetMessage.builder()
                            .messageType(new MessageType(Const.hall.NAME_HALL_REP))
                            .bytes(JSONObject.toJSONString(userDto).getBytes())
                            .build());
        }
    }
}

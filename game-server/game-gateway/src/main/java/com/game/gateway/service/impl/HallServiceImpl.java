package com.game.gateway.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.core.redis.RedisUtil;
import com.game.core.ws.clientconfig.WsSyncClient;
import com.game.core.ws.dto.AbsMessageType;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.gateway.service.HallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 12:02 2019/5/17 0017
 * @explain :
 */
@Slf4j
@Service
public class HallServiceImpl implements HallService {

    @Override
    public String loginGateWay(Object user, String token) {
        if (Objects.isNull(token) || Objects.isNull(user)) {
            return "未能找到对应的标识！& 用户信息为null";
        } else {
            String strToken = RedisUtil.get(token);
            //todo 判断各种token的格式
            if(Objects.nonNull(strToken) && strToken.startsWith(Const.gateWay.TOKEN)) {

                MessageType messageType = MessageType.builder()
                                .absMessageType(AbsMessageType.builder().serial(Const.number.FIVE).version((byte) Const.number.THREE).build())
                                .cmd(Const.hall.JOIN_HALL).build();
                NetMessage netMessage = NetMessage.builder()
                        .messageType(messageType)
                        .messageBody(MessageBody.builder().bytes(JSONObject.toJSONString(user).getBytes()).build())
                        .build();

                //接收消息的时候 我去初始化的客户的数据
                String s = WsSyncClient.sendMsgToGame(netMessage, Const.Addr.GATE_WAY);
                try {
                    JSONObject res = JSONObject.parseObject(s);
                    JSONObject resData = JSONObject.parseObject(res.getString("data"));
                    return resData.toJSONString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "解析数据失败！";
                }
            }
        }
        return "未能找到对应的标识！& 用户信息为null";
    }
}

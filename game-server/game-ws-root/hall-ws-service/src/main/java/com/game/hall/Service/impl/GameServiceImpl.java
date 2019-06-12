package com.game.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.common.constant.Const;
import com.game.common.constant.Errors;
import com.game.common.entity.user.User;
import com.game.common.mapper.UserMapper;
import com.game.common.redis.RedisUtil;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.core.ws.server.manager.WebSocket;
import com.game.hall.config.name.AbsServer;
import com.game.hall.dto.UserDto;
import com.game.hall.service.GameService;
import com.game.hall.service.abs.GameServiceImplAbrstarct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:47 2019/5/28 0028
 * @explain :
 */
@Slf4j
@Component
public class GameServiceImpl extends GameServiceImplAbrstarct implements GameService {

    @Autowired
    private UserMapper userMapper;
    
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
     * @Desc  :   网关加入游戏验证
     */
    @Override
    public void gateWayJoin(WebSocket webSocket, JSONObject jsonObject) {
        User s_user = null;
        System.out.println("jsonObject:"+jsonObject);
        Integer uId = jsonObject.getInteger("uId");
        s_user =  JSONObject.parseObject(RedisUtil.get(String.valueOf(uId)),User.class);
        if(Objects.isNull(s_user)) {
            s_user = userMapper.getOne(uId);
        }
        if(Objects.nonNull(uId) && Objects.nonNull(s_user)) {
            log.info("网关验证成功！");
            //数据加载进入集合
            Map<Integer, UserDto> concurrentHashMapUserDto = AbsServer.getConcurrentHashMapUserDto();
            concurrentHashMapUserDto.put(s_user.getUId(),UserDto.builder()
                    .uAccount(s_user.getUAccount())
                    .uEmail(s_user.getUEmail())
                    .uId(s_user.getUId())
                    .uName(s_user.getUName())
                    .uPhone(s_user.getUPhone())
                    .build());
            webSocket.send(NetMessage.builder().bytes(String.valueOf(s_user).getBytes()).messageType(new MessageType(Const.hall.JOIN_HALL_REP)).build());
        } else {
            log.info("网关验证失败！");
            webSocket.send(NetMessage.builder().bytes(Errors.no_false_error.getMsg().getBytes()).messageType(new MessageType(Const.hall.JOIN_HALL_REP)).build());
            return;
        }
    }
}

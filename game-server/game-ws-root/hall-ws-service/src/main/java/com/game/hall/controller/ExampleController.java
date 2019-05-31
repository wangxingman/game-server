package com.game.hall.controller;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.User;
import com.game.common.mapper.UserMapper;
import com.game.core.redis.RedisUtil;
import com.game.core.ws.clientConfig.WsSyncClient;
import com.game.core.ws.dto.AbsMessageType;
import com.game.core.ws.dto.MessageBody;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:24 2019/5/31 0031
 * @explain :   快速操作的方法【目前只做测试用】
 */
@Slf4j
@RestController
public class ExampleController extends BaseApi {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String addr = "127.0.0.1:8083";

    /**
     * @Author: wx
     * @Date : 下午 12:01 2019/5/31 0031
     * @params:
     * @Desc :   快速注册
     */
    @GetMapping("/fastRegister")
    @Transactional
    public Result fastRegister() {
        new RedisUtil(stringRedisTemplate);
        User rep_user = User.builder()
                .uName("张三").uAccount("凤舞九天")
                .uEmail("wangxing@163.com").uNumber(1231231)
                .uPass("123456").uPhone("123131")
                .createtime(new Date()).updatetime(new Date())
                .build();
        log.info("用户注册成功" + rep_user.getUId());
        long l = System.nanoTime();
        rep_user.setUToken(String.valueOf(l));
        userMapper.save(rep_user);
        RedisUtil.save(rep_user.getUToken(), JSONObject.toJSONString(rep_user), 5L);
        return success(rep_user);
    }

    /**
     * @Author: wx
     * @Date : 下午 12:01 2019/5/31 0031
     * @params:
     * @Desc :快速登陆
     */
    @PostMapping("/fastLogin")
    public Result fastLogin(@RequestBody User user) {
        User rep_user = userMapper.findByUEmailOrUPhoneAndUPass(user.getUEmail(), user.getUPhone(), user.getUPass());
        NetMessage netMessage = NetMessage.builder()
                .messageBody(MessageBody.builder().bytes(JSONObject.toJSONString(rep_user).getBytes()).build())
                .messageType(MessageType.builder().cmd(Const.hall.JOIN_HALL)
                        .absMessageType(AbsMessageType.builder().serial(Const.number.FIVE).version((byte) Const.number.THREE).build())
                        .build())
                .build();
        if (Objects.nonNull(rep_user)) {
            WsSyncClient.sendMsgToGame(netMessage, addr);
            return success(rep_user);
        }
        return error("用户登陆失败！");
    }

}

package com.game.hall.controller;

import com.alibaba.fastjson.JSONObject;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.constant.Const;
import com.game.common.entity.user.User;
import com.game.common.mapper.UserMapper;
import com.game.common.redis.RedisUtil;
import com.game.core.ws.clientconfig.WsSyncClient;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:24 2019/5/31 0031
 * @explain :   快速操作的方法【目前只做测试用】
 *              GM调试
 */
@Slf4j
@RestController
public class ExampleController extends BaseApi {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String addr = "127.0.0.1:8083";

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * @Author: wx
     * @Date  : 下午 12:36 2019/6/17 0017 
     * @params: 
     * @Desc  :    刷新配置中心
     */
    @GetMapping("/refresh")
    public void refresh(){
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置content_type为json要不然会报415的错误
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null,httpHeaders);
        // 以post方法访问真正的刷新链接
        ResponseEntity<String> stringResponseEntity = restTemplate().postForEntity("http://127.0.0.1:7001/actuator/bus-refresh",
                request, String.class);
    }

    @Value("${hall.port}")
    String port;

    /**
     * @Author: wx
     * @Date  : 上午 10:45 2019/6/17 0017 
     * @params: 
     * @Desc  : 测试bus的功能
     */
    @GetMapping("/exampleBus")
    public Object exampleBus() {
        System.out.println("获取到的值："+port);
        return port;
    }
    
    /**
     * @Author: wx
     * @Date : 下午 12:01 2019/5/31 0031
     * @params:
     * @Desc :   快速注册
     */
    @GetMapping("/fastRegister")
    @Transactional
    public Result fastRegister() {
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
        new RedisUtil(stringRedisTemplate);
        if(Objects.isNull(user)) {
             user = User.builder().uPhone("123131").uPass("123456").uEmail("wangxing@163.com").build();
        }
        User rep_user = userMapper.findByUEmailOrUPhoneAndUPass(user.getUEmail(), user.getUPhone(), user.getUPass());
        NetMessage netMessage = NetMessage.builder()
                .bytes(JSONObject.toJSONString(rep_user).getBytes())
                .messageType(new MessageType(Const.hall.JOIN_HALL))
                .build();
        try {
            RedisUtil.save(String.valueOf(rep_user.getUId()),JSONObject.toJSONString(rep_user),5L);
        } catch (Exception e) {
            log.info("加入缓存失败！");
            e.printStackTrace();
        }
        if (Objects.nonNull(rep_user)) {
            WsSyncClient.sendMsgToGame(netMessage, addr);
            return success(rep_user);
        }
        return error("用户登陆失败！");
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 3:52 2019/6/6 0006 
     * @params: 
     * @Desc  :  验证客户选择 名字
     */
    @PostMapping("/verifyName")
    public Result verifyName(@RequestBody User user) {
        NetMessage netMessage = NetMessage.builder()
                .bytes(JSONObject.toJSONString(user).getBytes())
                .messageType(new MessageType(Const.hall.NAME_HALL))
                .build();
        try {
            WsSyncClient.sendMsgToGame(netMessage, addr);
        } catch (Exception e) {
            log.info("客户验证名字失败！");
            e.printStackTrace();
        }
        return success("客户验证名字");
    }

   /**
    * @Author: wx
    * @Date  : 下午 2:49 2019/6/13 0013 
    * @params: 
    * @Desc  :  测试vue的请求
    */
    @PostMapping("/exampleVue")
    public Result exampleVue(@RequestBody User user) {
        String name = user.getUName();
        System.out.println("----------------"+name);
        return success("测试vue的请求","----");
    }

}

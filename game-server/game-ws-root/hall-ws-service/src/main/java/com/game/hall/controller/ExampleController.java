package com.game.hall.controller;

import com.alibaba.fastjson.JSONObject;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.constant.Const;
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

}

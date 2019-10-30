package com.game.log.rabbit;

import com.alibaba.fastjson.JSON;
import com.game.log.entity.SysLog;
import com.game.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: @
 * @Desc:
 * @Date: 下午 8:12 2019/10/29 0029
 * @Param null
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    SysLogService sysLogService;
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        SysLog sysLog=  JSON.parseObject(message, SysLog.class);
        sysLogService.saveLogger(sysLog);
        latch.countDown();
    }


}
package com.game.production.service.impl;

import com.game.model.Demo;
import com.game.model.spring.ServiceBClient;
import com.game.model.spring.ServiceCClient;
import com.game.production.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 5:23 2019/5/11 0011
 * @explain :
 */
@Service
@Slf4j
public class DemoServiceImpl  implements DemoService {


    @Autowired
    private  DemoMapper demoMapper;
    
    @Autowired
    private  ServiceBClient serviceBClient;

    @Autowired
    private  ServiceCClient serviceCClient;


    @Override
    public String execute(String value, String ex) {
        // ServiceB
        String dResp = serviceBClient.rpc(value);

        // ServiceC
        String eResp = serviceCClient.rpc(value);

        Demo demo = new Demo();
        demo.setDemoField(value);
        demo.setCreateTime(new Date());
        demoMapper.save(demo);

        return "String";
    }
}

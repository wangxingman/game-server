package com.game.config.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:19 2019/5/30 0030
 * @explain :
 */
@RestController
public class ExampleController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/getExample")
    public String getExample() {
        ExampleJpaimpl  exampleJpaimpl= new ExampleJpa();
        exampleJpaimpl.getExample();
        return "1231";
    }  
}

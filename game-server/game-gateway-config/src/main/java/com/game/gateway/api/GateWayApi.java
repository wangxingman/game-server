package com.game.gateway.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:33 2019/7/9 0009
 * @explain :
 */
@RestController
@RequestMapping("/gateWay")
public class GateWayApi {

    /**
     * @Author: wx
     * @Date  : 下午 2:35 2019/7/9 0009 
     * @params: 
     * @Desc  :  、、、、
     */
    @GetMapping("/example")
    public String example() {
        return "gateWay";
    }
}

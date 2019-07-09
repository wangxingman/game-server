package com.game.hall.api;

import com.game.common.comman.api.BaseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:34 2019/7/8 0008
 * @explain :
 */
@RestController
@RequestMapping("/exampleCopy")
public class ExampleGateWayCopyApi extends BaseApi {

    /**
     * @Author: wx
     * @Date  : 下午 7:37 2019/7/8 0008 
     * @params: 
     * @Desc  :
     */
    @GetMapping("/example")
    public String example() {
        return "1231";
    }
}

package com.game.gateway.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:27 2019/5/17 0017
 * @explain : 客户连接对应的大厅服务器
 */
@RestController
@RequestMapping("/gateWay")
public class EnterHallApi extends BaseApi {
    
    /**
     * @Author: wx
     * @Date  : 上午 11:42 2019/5/17 0017 
     * @params: 用户进入大厅
     * @Desc  :
     */
    @PostMapping("/enterHall")
    public Result enterHall() {
        return success();
    }
}

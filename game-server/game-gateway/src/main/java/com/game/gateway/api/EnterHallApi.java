package com.game.gateway.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.gateway.config.LocalSpringServiceManager;
import com.game.gateway.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @Author: wx
     * @Date  : 下午 8:18 2019/5/27 0027 
     * @params: 
     * @Desc  : feign转发 不太清楚 安全情况
     *          login登录成功调用此接口
     *          拿到对应的标识 取数据库查找
     */
    @GetMapping("/loginGateWay")
    public Result loginGateWay(@RequestBody Object user, @RequestParam("token") String token) {
        HallService hallService = LocalSpringServiceManager.getInstance().getHallService();
        String s = hallService.loginGateWay(user, token);
        return success(s);
    }

}

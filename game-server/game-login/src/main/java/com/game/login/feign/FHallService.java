package com.game.login.feign;

import com.game.login.feign.callback.FHallServiceCallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 8:30 2019/5/27 0027
 * @explain :
 */
@FeignClient(name="gateWay-service",fallback = FHallServiceCallBack.class)
public interface FHallService {

    /**
     * @Author: wx
     * @Date  : 下午 8:38 2019/5/27 0027 
     * @params: 
     * @Desc  :  调用gateWay的方法
     */
    @GetMapping("/loginGateWay")
    boolean loginGateWay(@RequestBody Object item, @RequestParam("token") String desc);
}

package com.game.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:53 2019/5/13 0013
 * @explain :
 */
@FeignClient(name="shop-service")
public interface FUserInfoService {

    @GetMapping("/removeUserInfo")
    String removeUserInfo();
}

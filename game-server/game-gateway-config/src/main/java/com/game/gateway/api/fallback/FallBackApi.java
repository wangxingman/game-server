package com.game.gateway.api.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/7/9 0009
 * @explain :
 */
@RestController
public class FallBackApi {

    /**
     * @Author: wx
     * @Date  : 上午 11:55 2019/7/9 0009 
     * @params: 
     * @Desc  :
     */
    @RequestMapping("/user/fallback")
    public Mono<String> fallback() {
        return Mono.just("service error, jump fallback");
    }
}

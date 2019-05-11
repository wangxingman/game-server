package com.game.model.spring;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Component
public interface ServiceCClient {

    @GetMapping("/rpc")
    String rpc(@RequestParam("value") String name);
}

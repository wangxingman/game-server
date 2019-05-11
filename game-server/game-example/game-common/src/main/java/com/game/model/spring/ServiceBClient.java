package com.game.model.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
public interface ServiceBClient {

    @GetMapping("/rpc")
    String rpc(@RequestParam("value") String name);
}

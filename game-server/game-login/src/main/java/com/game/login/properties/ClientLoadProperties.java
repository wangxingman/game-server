package com.game.login.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 8:50 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
@Component
@ConfigurationProperties("system.client")
public class ClientLoadProperties {
    private ClientProperties[] clients = {};
}

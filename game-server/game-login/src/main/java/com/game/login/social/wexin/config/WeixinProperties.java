package com.game.login.social.wexin.config;

import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:19 2019/6/10 0010
 * @explain :  自定义微信的服务提供商ID
 */
@Data
public class WeixinProperties {

    private String providerId = "wexin";

    private String appSecret;

    private String appId;
    
}

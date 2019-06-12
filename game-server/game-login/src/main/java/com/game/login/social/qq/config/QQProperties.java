package com.game.login.social.qq.config;

import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:17 2019/6/10 0010
 * @explain :  自定义QQ的服务提供商ID
 */
@Data
public class QQProperties  {

    private String appSecret;

    private String appId;
    
    private String providerId = "qq";
}

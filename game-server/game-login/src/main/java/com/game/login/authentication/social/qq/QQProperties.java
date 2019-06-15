package com.game.login.authentication.social.qq;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 12:14 2019/6/9 0009
 * @params:
 * @Desc  :  自定义QQ的服务提供商ID
 */
@Data
public class QQProperties  {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "qq";
}

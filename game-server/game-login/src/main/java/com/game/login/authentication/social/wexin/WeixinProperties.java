package com.game.login.authentication.social.wexin;

import lombok.Data;

/**
 * @author lvhaibao
 * @description 自定义微信的服务提供商ID
 * @date 2019/1/4 0004 9:47
 */
@Data
public class WeixinProperties  {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "weixin";

}

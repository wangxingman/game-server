package com.game.login.authentication.social.wexin;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:48 2019/7/3 0003 
 * @params: 
 * @Desc  :
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

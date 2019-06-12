package com.game.login.social.wexin.provider;

import com.game.login.social.wexin.config.WeixinOAuth2Template;
import com.game.login.social.wexin.service.Weixin;
import com.game.login.social.wexin.service.serviceImpl.WexinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:03 2019/6/11 0011
 * @explain :
 */
public class WeixinServiceProvider  extends AbstractOAuth2ServiceProvider<Weixin> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    

    /**
     * @Author: wx
     * @Date  : 上午 11:05 2019/6/11 0011 
     * @params: 
     * @Desc  :
     */
    public WeixinServiceProvider(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public Weixin getApi(String accessToken) {
        return new WexinImpl(accessToken);
    }
}

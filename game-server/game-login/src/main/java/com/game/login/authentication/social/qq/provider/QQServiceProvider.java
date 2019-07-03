package com.game.login.authentication.social.qq.provider;

import com.game.login.authentication.social.qq.service.QQService;
import com.game.login.authentication.social.qq.service.impl.QQServiceImpl;
import com.game.login.authentication.social.qq.template.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author: wx
 * @Date  : 下午 8:46 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {

    /**
     * 导向authorizeUrl地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 得到code之后，导向申请获取token
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;


    public QQServiceProvider(String appId, String appSecret) {
        //模板赋值
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQService getApi(String accessToken) {
        return new QQServiceImpl(accessToken, appId);
    }
}

package com.game.login.authentication.social.qq.factory;

import com.game.login.authentication.social.qq.apapter.QQAdapter;
import com.game.login.authentication.social.qq.provider.QQServiceProvider;
import com.game.login.authentication.social.qq.service.QQService;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author: wx
 * @Date  : 下午 12:14 2019/6/9 0009 
 * @params: 
 * @Desc  :  qq连接工厂
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {

    /**
     * 创建连接工厂
     *
     * 供应商  适配器 id 赋值工厂
     *
     * @param providerId 服务提供商
     * @param appId      serviceProvider
     * @param appSecret  apiAdapter
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}

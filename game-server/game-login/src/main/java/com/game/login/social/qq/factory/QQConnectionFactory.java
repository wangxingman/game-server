package com.game.login.social.qq.factory;

import com.game.login.social.qq.adapter.QQAdapter;
import com.game.login.social.qq.provider.QQServiceProvider;
import com.game.login.social.qq.service.QQService;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:08 2019/6/10 0010
 * @explain : QQ连接工厂
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {

    /**
     * 创建连接工厂
     *
     * @param providerId 服务提供商
     * @param appId      serviceProvider
     * @param appSecret  apiAdapter
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}

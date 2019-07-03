package com.game.login.authentication.social.qq;

import com.game.login.authentication.social.qq.factory.QQConnectionFactory;
import com.game.login.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 * @Author: wx
 * @Date  : 下午 8:47 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Configuration
@ConditionalOnProperty(prefix = "system.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        //获取 参数 进入连接 工厂
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
}

package com.game.see.config;

import com.game.see.alipay.AlipayProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:28 2019/8/9 0009
 * @explain :监听器 【观察者 模式】
 *           监听器>过滤器>servlet
 */
@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        AlipayProperties.loadProperties();
    }
}

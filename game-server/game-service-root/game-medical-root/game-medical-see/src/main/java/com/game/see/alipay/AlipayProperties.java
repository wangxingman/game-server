package com.game.see.alipay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:37 2019/8/10 0010
 * @explain :
 */
@Slf4j
@Component
public class AlipayProperties {


    /**
     * 保存加载配置参数
     */
    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    /**
     * 加载属性
     */
    public static void loadProperties() {
      log.info("监听器--------------------------所谓观察者模式！");
    }

}

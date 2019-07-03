package com.game.login.config;



import com.game.login.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wx
 * @Date  : 下午 5:26 2019/7/3 0003 
 * @params: 
 * @Desc  :  读取配置文件
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class PropertiesConfig {

}

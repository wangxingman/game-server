package com.game.login.config;



import com.game.login.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wx
 * @Date  : 上午 11:29 2019/6/10 0010 
 * @params: 
 * @Desc  :  注入yml文件加载
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class PropertiesConfig {

}

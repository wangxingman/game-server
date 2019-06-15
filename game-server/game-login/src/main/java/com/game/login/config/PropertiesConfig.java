package com.game.login.config;



import com.game.login.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lvhaibao
 * @descriptions
 * @date 2018/11/22 0022 11:54
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class PropertiesConfig {

}

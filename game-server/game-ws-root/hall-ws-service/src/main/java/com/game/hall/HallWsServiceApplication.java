package com.game.hall;

import com.game.core.utils.ManagerBanaderUtil;
import com.game.core.annotation.EnableNetty;
import com.game.hall.po.HallProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/5 0005
 */
@EnableCaching
@EnableNetty
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.game.common.mapper")
@EntityScan(basePackages = "com.game.common.entity")
@EnableConfigurationProperties(value = HallProperties.class)
public class HallWsServiceApplication {

    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(HallWsServiceApplication.class);
        springApplication.setBanner(new ManagerBanaderUtil());
        springApplication.run( args);
    }
}
    
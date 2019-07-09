package com.game.zuul;

import com.game.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author: wx
 * @Desc:
 * @Date: 下午 5:25 2019/5/20 0005
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication(exclude = { RabbitAutoConfiguration.class})
public class ZuulServiceApplication {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params: 
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class);
    }

    @Bean
    public void redisUtil() {
        new RedisUtil(stringRedisTemplate);
    }
}
    
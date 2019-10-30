package com.game.pay;

import com.game.pay.alipay.model.AlipayPropertiesModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: wx
 * @Date: 下午 7:20 2019/10/29 0029
 * @Desc:
 * @version: 支付系统测试
 */
@SpringBootApplication()
@EnableEurekaClient
@EnableConfigurationProperties(value = AlipayPropertiesModel.class)
public class PayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServerApplication.class, args);
    }
}

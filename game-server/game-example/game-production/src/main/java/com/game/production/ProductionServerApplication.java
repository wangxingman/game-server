package com.game.production;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:00 2019/5/11 0011
 * @explain :
 */
@SpringBootApplication
@EnableDistributedTransaction
public class ProductionServerApplication {
    /**
     * @Author: wx
     * @Desc:
     * @Date: 下午 5:26 2019/5/5 0005
     * @params:
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductionServerApplication.class, args);
    }
}

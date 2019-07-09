package com.game.zuul.netty;

import org.springframework.stereotype.Component;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:44 2019/5/21 0021
 * @explain :
 */
@Component
public class LocalNetService extends AbstractNetService{

    @Override
    public void startup() {
        this.initChannlInitializer();
        this.initNetService();
    }
}

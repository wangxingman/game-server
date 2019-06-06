package com.game.hall.state;

import com.game.core.thread.AbstractRunnable;
import com.game.hall.ws.InitDispatcher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @auther : wx
 * @Desc :
 * @Date :  下午 4:11 2019/5/17 0017
 * @explain :
 */
@Slf4j
@Data
public class NettyRunnable extends AbstractRunnable {

    /**
     * @Author: wx
     * @Date  : 下午 4:13 2019/5/17 0017 
     * @params: 
     * @Desc  : 构造方法执行线程方法
     */
    public NettyRunnable() {
        this.startThread();
    }

    @Override
    public void run() {
        new InitDispatcher().init();
    }

}

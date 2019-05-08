package com.game.sg.state;

import com.game.core.thread.AbstractRunnable;
import com.game.sg.common.netty.NettyStart;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 上午 10:22 2019/5/8 0008
 */
@Slf4j
public class NettyRunnable extends AbstractRunnable {

    public NettyRunnable() {
        this.startThread();
    }

    @Override
    public void run() {
        log.info("线程启动netty服务器！");
        NettyStart.startNetty();
    }
}

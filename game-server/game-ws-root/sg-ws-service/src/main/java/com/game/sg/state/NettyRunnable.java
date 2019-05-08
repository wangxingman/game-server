package com.game.sg.state;

import com.game.common.Const.Const;
import com.game.core.properties.WsConfig;
import com.game.core.thread.AbstractRunnable;
import com.game.sg.common.netty.NettyStart;
import lombok.extern.slf4j.Slf4j;



/**
 * @Auther: wx
 * @Desc :  加载netty启动线程
 * @Date : 上午 10:22 2019/5/8 0008
 */
@Slf4j
public class NettyRunnable extends AbstractRunnable  {

    private Integer port;
    
    private NettyStart getNettyStart() {
        return new NettyStart();
    }
    
    public NettyRunnable() {
        String path = null;
        try {
            path = NettyRunnable.class.getResource(Const.path.PATH).getPath();
        } catch (Exception e) {
            log.error("没有很好的加载文件！");
            e.printStackTrace();
        }
        WsConfig.initWs(path);
        this.startThread();
    }

    @Override
    public void run() {
        this.port = Integer.parseInt(WsConfig.getProperty(Const.ws.PORT));
        log.info("线程启动netty服务器！");
        getNettyStart().startNetty(port);
    }
}

package com.game.hall.ws;

import com.game.core.ws.initializer.WsInitializer;
import com.game.core.ws.server.WebSocketServerInitializer;
import com.game.hall.po.HallProperties;
import com.game.hall.state.NettyRunnable;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: wx
 * @Desc:   启动netty服务器
 * @Date:   下午 4:48 2019/5/5 0005
 *          本来想使用spring的EnableConfigurationProperties
 *          但是我使用的方法是在servlet之前加载 不能注入容器
 */
@Slf4j
@Component
public class NettyStart implements WsInitializer, DisposableBean {

    @Autowired
    private HallProperties hallProperties;

    private WebSocketServerInitializer webSocketServerInitializer;

    private  EventLoopGroup bossGroup;
    
    private  EventLoopGroup workerGroup;

    public NettyStart() {
        this.webSocketServerInitializer = new WebSocketServerInitializer();
    }

    @Override
    public void init() throws Exception {
        this.startNetty(hallProperties);
    }

    /**
     * @Author: wx
     * @Desc:   启动netty服务器
     * @Date: 下午 5:47 2019/5/5 0005
     * @params:
     * @param hallProperties
     */
    public void startNetty(HallProperties hallProperties) {
         bossGroup = new NioEventLoopGroup(1);
         workerGroup = new NioEventLoopGroup();
        try{

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(webSocketServerInitializer);

            Channel ch = b.bind(hallProperties.getPort()).sync().channel();
            log.info("服务开启"+"://127.0.0.1:" + hallProperties.getPort() + '/');
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void destroy() throws Exception {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        log.info("服务已经关闭");
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 4:29 2019/5/17 0017 
     * @params: 
     * @Desc  : servlet加载之前 初始化 【不知道可不可行】
     */
    @PostConstruct
    public void initNetty() {
        log.info("------------------------------");
        //在这里可以加载机器人的照片 fastdfs
        new NettyRunnable();
    }
}

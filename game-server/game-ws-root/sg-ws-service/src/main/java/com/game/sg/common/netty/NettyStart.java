package com.game.sg.common.netty;

import com.game.common.dto.NettyParams;
import com.game.sg.state.NettyRunnable;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @Auther: wx
 * @Desc:   启动netty服务器
 * @Date:   下午 4:48 2019/5/5 0005
 */
@Slf4j
public class NettyStart {

    @Autowired
    private static NettyParams nettyParams;

    /**
     * @Author: wx
     * @Desc:   启动netty服务器
     * @Date: 下午 5:47 2019/5/5 0005
     * @params:
     */
    public static void startNetty() {
        log.info("开始设置netty服务器！");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketServerInitializer());

            Channel ch = b.bind(nettyParams.getPort()).sync().channel();
            log.info("://127.0.0.1:" + nettyParams.getPort() + '/');
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PostConstruct
    public void initNetty() {
        new NettyRunnable();
    }
}

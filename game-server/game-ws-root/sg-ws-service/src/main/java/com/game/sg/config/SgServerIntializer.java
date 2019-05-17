package com.game.sg.config;

import com.codingapi.txlcn.txmsg.dto.ManagerProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:04 2019/5/17 0017
 * @explain :
 */
@Service
@Slf4j
public class SgServerIntializer  implements ServerInitializer, DisposableBean {

    private final SgServerChannelInitializer sgServerChannelInitializer;

    private EventLoopGroup workerGroup;
    private NioEventLoopGroup bossGroup;

    @Autowired
    public SgServerIntializer(SgServerChannelInitializer sgServerChannelInitializer) {
        this.sgServerChannelInitializer = sgServerChannelInitializer;
    }


    @Override
    public void init(ManagerProperties managerProperties) {
/*       sgServerChannelInitializer.setManagerProperties(managerProperties);*/
        
        int port = managerProperties.getRpcPort();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(sgServerChannelInitializer);

            if (StringUtils.hasText(managerProperties.getRpcHost())) {
                b.bind(managerProperties.getRpcHost(), managerProperties.getRpcPort());
            } else {
                b.bind(port);
            }
            log.info("Socket started on {}:{} ",
                    StringUtils.hasText(managerProperties.getRpcHost()) ? managerProperties.getRpcHost() : "0.0.0.0", port);

        } catch (Exception e) {
            // Shut down all event loops to terminate all threads.
            e.printStackTrace();
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

        log.info("server was down.");
    }
}

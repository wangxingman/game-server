package com.game.core.ws.clientConfig;

import com.alibaba.fastjson.JSON;
import com.game.core.ws.clientConfig.hanlder.WsSyncHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:41 2019/5/16 0016
 * @explain :连接对应的服务器【可以使用tm的那种方式】
 */
public class WsSyncClient {
    public static String sendMsgToGame(Object obj,String addrs) {
        String s = "";
        try {
            addrs = new String(addrs.getBytes(), "UTF-8");
            System.out.println("addrs:" + addrs);
            if (false) {
                s = sendAndClose("wss://" + addrs+"/websocket", obj);
            } else {
                s = sendAndClose("ws://" + addrs+"/websocket", obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String sendAndClose(String wsUrl, Object obj) throws Exception {
        final StringBuffer message = new StringBuffer();

        URI uri = new URI(wsUrl);
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        final String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
        final int port;
        if (uri.getPort() == -1) {
            if ("http".equalsIgnoreCase(scheme)) {
                port = 80;
            } else if ("https".equalsIgnoreCase(scheme)) {
                port = 443;
            } else {
                port = -1;
            }
        } else {
            port = uri.getPort();
        }
        if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
            throw new RuntimeException("Only WS(S) is supported.");
        }

        final boolean ssl = "wss".equalsIgnoreCase(scheme);
        final SslContext sslCtx;
        if (ssl) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            final WsSyncHandler handler = new WsSyncHandler(message, WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));

            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ChannelPipeline p = ch.pipeline();
                    if (sslCtx != null) {
                        p.addLast(sslCtx.newHandler(ch.alloc(), host, port));

                    }
                    //加个超时处理..
                    p.addLast(new ReadTimeoutHandler(5));
                    p.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192), handler);
                }
            });

            Channel ch = b.connect(uri.getHost(), port).sync().channel();
            handler.handshakeFuture().sync();
            WebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(obj));
            ch.writeAndFlush(frame);
            ch.writeAndFlush(new CloseWebSocketFrame());
            ch.closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
        System.out.println("2---------------"+message.toString());
        return message.toString();
    }
}

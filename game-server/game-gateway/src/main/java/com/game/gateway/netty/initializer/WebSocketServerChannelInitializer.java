package com.game.gateway.netty.initializer;

import com.game.gateway.netty.hanlder.AsyncWebSocketFrameServerHandler;
import com.game.gateway.netty.hanlder.WebSocketServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 12:06 2019/5/21 0021
 * @explain :
 */
public class WebSocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    public static final String CurrentWebSocketServerHandler = "WebSocketServerHandler";
    public static final String WebSocketFrameServerHandler = "WebSocketFrameServerHandler";

    private  SslContext sslContext;

    public WebSocketServerChannelInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipLine = socketChannel.pipeline();
        //添加管道安全验证
        if(sslContext != null){
            channelPipLine.addLast(sslContext.newHandler(socketChannel.alloc()));
        }
        //编码方式
        channelPipLine.addLast("encoder", new HttpResponseEncoder());
        channelPipLine.addLast("decoder", new HttpRequestDecoder());
        channelPipLine.addLast(new HttpObjectAggregator(65536));

        channelPipLine.addLast(CurrentWebSocketServerHandler, new WebSocketServerHandler());
        channelPipLine.addLast(WebSocketFrameServerHandler, new AsyncWebSocketFrameServerHandler());
    }
}

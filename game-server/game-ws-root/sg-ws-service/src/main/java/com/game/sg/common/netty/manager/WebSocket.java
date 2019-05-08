package com.game.sg.common.netty.manager;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Auther: wx
 * @Desc :  websocket连接封装
 * @Date : 下午 8:00 2019/5/5 0005
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class WebSocket {

    /**
     * @Author: wx
     * @Desc  :  每个ChannelHandler通过add方法加入到ChannelPipeline中去的时候，
     *           会创建一个对应的ChannelHandlerContext，并且绑定，
     *           ChannelPipeline实际维护的是ChannelHandlerContext 的关系
     * @Date  : 下午 8:05 2019/5/5 0005
     */
    private ChannelHandlerContext ctx;

    private HttpRequest request;

    private long sessionId;

    private long createTime;

    private long updateTime;

    private String remoteAddr;

    private Integer userId;

    public WebSocket(ChannelHandlerContext ctx, HttpRequest request, long sessionId) {
        this.setRequest(request);
        this.setCtx(ctx);
        this.setSessionId(sessionId);
        long date = System.currentTimeMillis();
        this.setCreateTime(date);
        this.setUpdateTime(date);
        InetSocketAddress addr = (InetSocketAddress)ctx.channel().remoteAddress();
        this.remoteAddr = addr.getAddress().getHostAddress();
    }

    /**
     * @Author: wx
     * @Desc  : 关闭这条连接 
     * @Date  : 下午 8:41 2019/5/5 0005
     * @params:
     */
    public ChannelFuture close() {
        WebSocket websoket = WebSocketManager.getWebSocket(userId);
        if (websoket != null && websoket.getSessionId() == sessionId) {
            WebSocketManager.remove(this);// 根据SessionId来清除WebSocket
        }
        ChannelFuture future = this.ctx.channel().close();
        future.addListener(ChannelFutureListener.CLOSE);
        if (log.isDebugEnabled()) {
            log.debug("close websocket, userid=" + userId + " sessionId:" + this.sessionId);
        }
        return future;
    }

}

package com.game.core.ws.server.Manager;

import com.alibaba.fastjson.JSON;
import com.game.core.ws.dto.NetMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Objects;

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
            // 根据SessionId来清除WebSocket
            WebSocketManager.remove(this);
        }
        ChannelFuture future = this.ctx.channel().close();
        future.addListener(ChannelFutureListener.CLOSE);
        if (log.isDebugEnabled()) {
            log.debug("close websocket, userid=" + userId + " sessionId:" + this.sessionId);
        }
        return future;
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:46 2019/5/29 0029 
     * @params: 
     * @Desc  :发送消息  
     */
    public ChannelFuture send(String message) {
        if(Objects.isNull(message)) {
             log.info("数据错误！");
        }
        ChannelFuture channelFuture = this.ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
        return channelFuture;
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:50 2019/5/29 0029 
     * @params: 
     * @Desc  :  发送消息体
     */
    public ChannelFuture sendAndFlush(NetMessage response) {
        if(Objects.isNull(response)) {
            log.info("数据错误！");
        }
        ChannelFuture future = this.ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
        return future;
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:18 2019/5/29 0029 
     * @params: 
     * @Desc  :发送二进制的消息
     */
    public boolean send(byte[] byteMsg) {
        if(Objects.isNull(byteMsg)) {
            log.info("数据错误！");
        }
        ByteBuf buffer = Unpooled.buffer(byteMsg.length);
        buffer.writeBytes(byteMsg);
        ChannelFuture future = ctx.channel().writeAndFlush(new BinaryWebSocketFrame(buffer));
        return true;
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:48 2019/5/29 0029 
     * @params: 
     * @Desc  :刷新管道
     */
    public void flush() {
        this.ctx.channel().flush();
    }

    
}

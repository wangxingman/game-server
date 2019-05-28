package com.game.gateway.netty.hanlder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import lombok.Data;

import java.util.Objects;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 6:48 2019/5/27 0027
 * @explain :初始化http请求
 */
@Data
public class WebSocketServerHandler extends SimpleChannelInboundHandler<HttpRequest> {


    private static final String WEBSOCKET_PATH = "/websocket";

    private WebSocketServerHandshaker handshaker;
    
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        hanldeHttpRequest(channelHandlerContext,httpRequest);
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:09 2019/5/27 0027 
     * @params: 
     * @Desc  :  read到0个字节或者是read到的字节数小于buffer的容量，满足以上条件就会调用channelReadComplete方法
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:14 2019/5/27 0027 
     * @params: 
     * @Desc  : 异常返回信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:05 2019/5/27 0027 
     * @params: 
     * @Desc  : http请求的操作
     */
    private void hanldeHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }
        if(req.method() != HttpMethod.GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
            return;
        }
        //url形式拦截
        if ("/favicon.ico".equals(req.uri()) || "/".equals(req.uri())) {
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND);
            sendHttpResponse(ctx, req, res);
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketLocation(req), null, true, 5 * 1024 * 1024);
        //创建一个新的握手
        handshaker = wsFactory.newHandshaker(req);
        if(Objects.nonNull(handshaker)) {
            handshaker.handshake(ctx.channel(), req);
        }else {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 7:18 2019/5/27 0027 
     * @params: 
     * @Desc  : 获取url
     */
    private String getWebSocketLocation(HttpRequest req) {
        String location =  req.headers().get(HttpHeaderNames.HOST) + WEBSOCKET_PATH;
        //todo 验证ssl
        boolean sslFlag = false;
        if (sslFlag) {
            return "wss://" + location;
        } else {
            return "ws://" + location;
        }
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:10 2019/5/27 0027 
     * @params: 
     * @Desc  : 返回消息
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, FullHttpResponse res) {
        if(res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            ByteBuf byteBuf = res.content();
            byteBuf.writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        //监听返回
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}

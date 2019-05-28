package com.game.hall.netty;

import com.alibaba.fastjson.JSONObject;
import com.game.hall.netty.manager.WebSocket;
import com.game.hall.netty.manager.WebSocketHandler;
import com.game.core.ws.dto.NetMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicLong;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.HOST;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 6:53 2019/5/5 0005
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final String WEBSOCKET_PATH = "/websocket";

    private static AtomicLong SIDS = new AtomicLong(1);
    
    private WebSocketServerHandshaker handShaker;

    private WebSocketHandler webSocketHandler;

    private WebSocket webSocket;
    
    /**
     * @Author: wx
     * @Desc  :  建立连接
     * @Date  : 下午 7:07 2019/5/5 0005
     * @params:
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx,(FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx,(WebSocketFrame) msg);
        }
    }

    /**
     * @Author: wx
     * @Desc:   http建立连接
     * @Date: 下午 7:07 2019/5/5 0005
     * @params:
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if(!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }
        //必须是get请求
        if(req.method() != HttpMethod.GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
            return;
        }
        //发送演示页面和favicon.ico
        if("/".equals(req.uri())) {
            boolean debug = true;
            if(debug) {
                ByteBuf content = WebSocketServerIndexPage.getContent(getWebSocketLocation(req));
                FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
                res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
                HttpHeaders.setContentLength(res, content.readableBytes());
                sendHttpResponse(ctx, req, res);
            }
        }
        if ("/favicon.ico".equals(req.uri())) {
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND);
            sendHttpResponse(ctx, req, res);
            return;
        }
        //握手
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketLocation(req), null, true);
        handShaker = wsFactory.newHandshaker(req);
        if (handShaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handShaker.handshake(ctx.channel(), req);
        }
        //websocket的操作器
        webSocketHandler = new WebSocketHandler();
        webSocket = new WebSocket(ctx, req, SIDS.getAndIncrement());
        webSocketHandler.onConnect(webSocket);
        restartHeartbeat();
    }
    
    /**
     * @Author:  wx
     * @Desc  :  启动或重新启动心跳检测
     * @Date  : 下午 8:08 2019/5/5 0005 
     * @params: 
     */
    private void restartHeartbeat() {

        
    }

    /**
     * @Author: wx
     * @Desc  :
     * @Date  : 下午 7:08 2019/5/5 0005
     * @params: 
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if(frame instanceof CloseWebSocketFrame) {
            //关闭握手器
            handShaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            webSocketHandler.onClose(webSocket);
        }
        if(frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if(!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass()
                    .getName()));
        }
        //发送消息过来 根据协议完成操作
        String message = ((TextWebSocketFrame) frame).text();
        webSocket.setUpdateTime(System.currentTimeMillis());
        NetMessage request = JSONObject.parseObject(message, NetMessage.class);
        webSocketHandler.onMessage(webSocket, request);
        restartHeartbeat();
    }
    
    /**
     * @Author: wx
     * @Desc  :
     * @Date  : 下午 7:12 2019/5/5 0005 
     * @params: 
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        if(res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release(); //释放
            HttpHeaders.setContentLength(res, res.content().readableBytes());
        }
        //netty的io操作异步 不能立即返回
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        //连接状态 open 返回 true
        if(!HttpHeaders.isKeepAlive(req) || res.status().code() != 200) {
            //以便在某个操作完成时（无论是否成功）得到通知。
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    
    private static String getWebSocketLocation(FullHttpRequest req) {
        String location =  req.headers().get(HOST) + WEBSOCKET_PATH;
        return "ws://" + location;
//        if (SSL) {
//            return "wss://" + location;
//        } else {
//            return "ws://" + location;
//        }
    }


}

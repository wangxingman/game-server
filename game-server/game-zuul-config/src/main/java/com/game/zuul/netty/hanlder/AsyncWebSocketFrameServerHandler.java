package com.game.zuul.netty.hanlder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 6:49 2019/5/27 0027
 * @explain : 初始化websocket请求
 */
@Slf4j
public class AsyncWebSocketFrameServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
   
    /**
     * @Author: wx
     * @Date  : 下午 6:53 2019/5/28 0028 
     * @params: 
     * @Desc  :
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        handleWebSocketFrame(ctx, msg);
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            WebSocketServerHandler webSocketServerHandler = (WebSocketServerHandler) ctx.pipeline().get("webSocketServerHandler");
            WebSocketServerHandshaker handshaker = webSocketServerHandler.getHandshaker();
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        if (frame instanceof PingWebSocketFrame) {
            ctx.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (frame instanceof TextWebSocketFrame) {
            // Echo the frame
            ctx.write(frame.retain());
            return;
        }
        //todo protobuf 协议
        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
            ByteBuf buf = binaryWebSocketFrame.content();
            //开始解析
            try {
            } catch (CodecException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:23 2019/5/27 0027 
     * @params: 
     * @Desc  :
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:23 2019/5/27 0027 
     * @params: 
     * @Desc  :
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
}

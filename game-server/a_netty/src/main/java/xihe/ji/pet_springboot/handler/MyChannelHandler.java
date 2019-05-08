package xihe.ji.pet_springboot.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xihe.ji.pet_springboot.dao.MessageMapper;
import xihe.ji.pet_springboot.pojo.BackMessage;
import xihe.ji.pet_springboot.pojo.Message;
import xihe.ji.pet_springboot.util.GlobalUserUtil;
import xihe.ji.pet_springboot.util.TransformationUtil;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.Set;

public class MyChannelHandler extends SimpleChannelInboundHandler<Object> {


    public MyChannelHandler(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    private MessageMapper messageMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyChannelHandler.class);

    private static final String URI = "websocket";

    private WebSocketServerHandshaker handshaker ;

    /**
     * 连接上服务器
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【增加链接】====>"+ctx.channel().id());
        GlobalUserUtil.channels.add(ctx.channel());
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【删除链接】====>"+ctx.channel().id());
        try {
            Set<String > keys= GlobalUserUtil.USERMAP.keySet();
            for(String  key:keys){
                if(GlobalUserUtil.USERMAP.get(key)==ctx.channel().id()){
                    GlobalUserUtil.USERMAP.remove(key);
                }
            }
        }catch (Exception e){

        }
        GlobalUserUtil.channels.remove(ctx.channel());
    }

    /**
     * 连接异常   需要关闭相关资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("【系统异常】======>"+cause.toString());
        ctx.close();
        ctx.channel().close();
    }

    /**
     * 活跃的通道  也可以当作用户连接上客户端进行使用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【活跃通道】=====>"+ctx.channel());
    }

    /**
     * 不活跃的通道  就说明用户失去连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【不活跃的通道】=====>"+ctx.channel());
    }

    /**
     * 这里只要完成 flush
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 这里是保持服务器与客户端长连接  进行心跳检测 避免连接断开
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()){
                //读空闲（服务器端）
                case READER_IDLE:
                    LOGGER.info("【"+ctx.channel().remoteAddress()+"】读空闲（服务器端）");
                    ctx.writeAndFlush(ping);
                    break;
                    //写空闲（客户端）
                case WRITER_IDLE:
                    LOGGER.info("【"+ctx.channel().remoteAddress()+"】写空闲（客户端）");
                    ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    LOGGER.info("【"+ctx.channel().remoteAddress()+"】读写空闲");
                    break;
                    default:break;
            }
        }
    }

    /**
     * 收发消息处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            doHandlerHttpRequest(ctx,(HttpRequest) msg);
        }else if(msg instanceof WebSocketFrame){
            doHandlerWebSocketFrame(ctx,(WebSocketFrame) msg);
        }
    }

    /**
     * websocket消息处理
     * @param ctx
     * @param msg
     */
    private void doHandlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) {
        //判断msg 是哪一种类型  分别做出不同的反应
        if(msg instanceof CloseWebSocketFrame){
            LOGGER.info("【关闭】");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
            return ;
        }
        if(msg instanceof PingWebSocketFrame){
            LOGGER.info("【ping】");
            PongWebSocketFrame pong = new PongWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(pong);
            return ;
        }
        if(msg instanceof PongWebSocketFrame){
            LOGGER.info("【pong】");
            PingWebSocketFrame ping = new PingWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(ping);
            return ;
        }
        if(!(msg instanceof TextWebSocketFrame)){
            LOGGER.info("【不支持二进制】");
            throw new UnsupportedOperationException("不支持二进制");
        }
        String ToMsg=((TextWebSocketFrame) msg).text();
        JSONObject jsonObject=JSONObject.fromObject(ToMsg);
        if(jsonObject.keySet().contains("ping")){
            JSONObject object=new JSONObject();
            object.put("pong","ok");
            ctx.channel().writeAndFlush(new TextWebSocketFrame(object.toString()));
        }else {
            LOGGER.info("正在发送的消息：\t" + ToMsg);
            //可以对消息进行处理
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.fromObject(GlobalUserUtil.sendMsg(ToMsg, messageMapper, ctx.channel().id())).toString()));
        }
    }


    /**
     * wetsocket第一次连接握手
     * @param ctx
     * @param request
     */
    private void doHandlerHttpRequest(ChannelHandlerContext ctx, HttpRequest request) {
        // http 解码失败
        if(!request.getDecoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))){
            sendHttpResponse(ctx, (FullHttpRequest) request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
        }
        //可以获取msg的uri来判断
        String uri = request.getUri();
        String[] ss=uri.split("/");
        if(ss.length<3){
            ctx.close();
        }
        if(!ss[1].equals(URI)){
            ctx.close();
        }
//        System.out.println(ss[3]+"|"+ctx.channel().id());
        // 增加用户
        String uuid=null;
        try {
            uuid=ss[3];
            GlobalUserUtil.USERMAP.put(ss[3],ctx.channel().id());
        }catch (Exception e){
            uuid=ss[2];
            GlobalUserUtil.USERMAP.put(ss[2],ctx.channel().id());
        }
        ctx.attr(AttributeKey.valueOf("type")).set(uri);
        //可以通过url获取其他参数
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                "ws://"+request.headers().get("Host")+"/"+URI+"",null,false
        );
        handshaker = factory.newHandshaker(request);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }
        //进行连接
        handshaker.handshake(ctx.channel(), (FullHttpRequest) request);
        //可以做其他处理
        String msg= pushMsg(uuid);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
        LOGGER.info("未推送的消息:"+msg);
        messageMapper.PushALlMessage(uuid);
        LOGGER.info("正在解析链接的用户 。。。\n 当前用于的用户:"+GlobalUserUtil.USERMAP.size());
    }

    private  String pushMsg(String uuid){
        List<Message> messages=messageMapper.selectNoPushMessage(uuid);
        JSONArray jsonArray=new JSONArray();
        for(Message message:messages){
            jsonArray.add(GlobalUserUtil.getJsonMessage(message));
        }
        return jsonArray.toString();
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}

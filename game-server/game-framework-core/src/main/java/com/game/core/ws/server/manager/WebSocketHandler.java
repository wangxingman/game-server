package com.game.core.ws.server.manager;

import com.game.common.Const.Const;
import com.game.common.Const.Errors;
import com.game.common.dto.NettyParams;
import com.game.core.ws.dto.AbsMessageType;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.core.ws.server.BaseDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 7:58 2019/5/5 0005
 */
@Slf4j
public class WebSocketHandler {

    @Autowired
    private NettyParams nettyParams;
    
    /**
     * @Author: wx
     * @Desc : 连接成功
     * @Date : 下午 8:02 2019/5/5 0005
     * @params:
     */
    public void onConnect(final WebSocket webSocket) {
        log.info("onConnect:sessionId=" + webSocket.getSessionId());
    }

    /**
     * @Author: wx
     * @Desc : 关闭连接
     * @Date : 下午 8:45 2019/5/5 0005
     * @params:
     */
    public void onClose(WebSocket webSocket) {
        log.info("onClose:sessionId=" + webSocket.getSessionId());
        WebSocketManager.remove(webSocket);
        webSocket.close();
    }

    /**
     * @Author: wx
     * @Desc :
     * @Date : 下午 8:51 2019/5/5 0005
     * @params:
     */
    public void onMessage(WebSocket webSocket, NetMessage request) {
        MessageType messageType = request.getMessageType();
        if(Objects.isNull(messageType)) {
            webSocket.send(String.valueOf(Errors.no_false_error));
        }
        AbsMessageType absMessageType = messageType.getAbsMessageType();
        if(absMessageType.getSerial()!= Const.number.FIVE && absMessageType.getLength()!=(short)Const.number.THREE ) {
            webSocket.send(String.valueOf(Errors.no_false_error));
        }
        Map<MessageType, Handler> localMap = BaseDispatcher.localMap;
        Handler handler = localMap.get(messageType);
        if (handler != null) {
            handler.handle(webSocket, request);
        } else {
            webSocket.close();
        }
    }

  /**
   * @Author: wx
   * @Date  : 上午 10:28 2019/6/3 0003 
   * @params: 
   * @Desc  :  pd 协议接收
   */
    public void onMessage(WebSocket webSocket, byte[] message) {

        try {
            Class<?> c = Class.forName("com.beiyou.ws.PBMsgManager");
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals("onMessage")) {
                    m.invoke(null, webSocket, message);
                    return;
                }
            }
        } catch (Exception e) {
            webSocket.close();
            e.printStackTrace();
        }
    }
    
    /**
     * @Author: wx
     * @Date  : 上午 10:27 2019/6/3 0003
     * @params:
     * @Desc  :管道关闭  连接关闭
     */
    public void onError(WebSocket webSocket, Throwable cause) {
        log.info("onError:sessionId=" + webSocket.getSessionId() + ";cause=" + cause.getMessage());
        webSocket.close();
        WebSocketManager.remove(webSocket);
    }

}

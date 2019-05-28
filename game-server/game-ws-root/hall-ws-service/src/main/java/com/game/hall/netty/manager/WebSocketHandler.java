package com.game.hall.netty.manager;

import com.game.common.dto.NettyParams;
import com.game.hall.netty.InitDispatcher;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
             //todo 判断消息 前缀 不合适 不允许
            
        }
        Map<MessageType, Handler> localMap = InitDispatcher.localMap;
        Handler handler = localMap.get(messageType);
        if (handler != null) {
            handler.handle(webSocket, request);
        } else {
            webSocket.close();
        }
    }
}

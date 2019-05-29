package com.game.core.ws.server.Manager;

import com.game.common.Const.Const;
import com.game.common.Const.Errors;
import com.game.common.dto.NettyParams;
import com.game.core.ws.dto.AbsMessageType;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.game.core.ws.server.BaseDispatcher;
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
            webSocket.send(String.valueOf(Errors.no_false_error));
        }
        //todo 这里数据类型可以放在yml文件里面
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
}

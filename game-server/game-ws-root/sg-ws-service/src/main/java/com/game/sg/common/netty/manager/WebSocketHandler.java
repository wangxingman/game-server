package com.game.sg.common.netty.manager;

import com.game.common.dto.DataPacket;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 7:58 2019/5/5 0005
 */
@Slf4j
public class WebSocketHandler {

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
    public void onMessage(WebSocket webSocket, DataPacket request) {
        Handler handler = Dispatcher.getDispatch(request.getCmd());
        if (handler != null) {
            handler.handle(webSocket, request);
        } else {
            log.info("Cmd error." + request.getCmd());
            webSocket.close();
        }
    }
}

package com.game.sg.common.netty.manager;

import com.game.common.dto.DataPacket;
import com.game.common.dto.NettyParams;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

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
    public void onMessage(WebSocket webSocket, DataPacket request) {
        Map<Integer, String> localMap = nettyParams.getLocalMap();
        String data = localMap.get(request.getAgreementNo());
        Handler handler = null;
        try {
             handler = (Handler) Class.forName(data).newInstance();
        } catch (ClassNotFoundException e) {
            log.error("转换类型失败！");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("参数错误！");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (handler != null) {
            handler.handle(webSocket, request);
        } else {
            log.info("Cmd error." + request.getAgreementNo());
            webSocket.close();
        }
    }
}

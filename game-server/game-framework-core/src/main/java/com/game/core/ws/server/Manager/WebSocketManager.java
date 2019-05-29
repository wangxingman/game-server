package com.game.core.ws.server.Manager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:33 2019/5/5 0005
 */
public class WebSocketManager {

    /** 保存userid,websocket */
    private static final ConcurrentHashMap<String, WebSocket> websockets = new ConcurrentHashMap<String, WebSocket>();

    private static String getWsSocketKey(int userId) {
        return String.valueOf(userId);
    }
    
    /**
     * @Author: wx
     * @Desc  :
     * @Date  : 下午 8:39 2019/5/5 0005 
     * @params: 
     */
    public static void remove(WebSocket webSocket) {
        if (webSocket == null) {
            return;
        }
        int userId = webSocket.getUserId();
        if (userId > 0) {
            websockets.remove(getWsSocketKey(userId));
            //CacheUtils.delUserOnline(userId);
            //Offline.getInstance().process(webSocket);
        }
    }

    /**
     * @Author: wx
     * @Desc  : 获取这条连接
     * @Date  : 下午 8:41 2019/5/5 0005
     * @params:
     */
    public static WebSocket getWebSocket(int userId) {
        return websockets.get(getWsSocketKey(userId));
    }
    
}

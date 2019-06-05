package com.game.core.ws.server.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wx
 * @Date  : 上午 10:42 2019/6/3 0003 
 * @params: 
 * @Desc  :   用户在线 当前的用户于对应的 webSocket绑定
 */
public class WebSocketManager {

    /** 保存userid,websocket */
    private static final ConcurrentHashMap<String, WebSocket> websockets = new ConcurrentHashMap<>();

    private static String getWsSocketKey(int userId) {
        return String.valueOf(userId);
    }

    /**
     * @Author: wx
     * @Date  : 上午 10:40 2019/6/3 0003 
     * @params: 
     * @Desc  :绑定服务
     *          查看用户 是否在别的服务器 如果在通知 踢掉
     *          用户加入现在的游戏服 添加在线记录
     */
    public static void bindWebSocket(int userId, WebSocket webSocket,int gameId,int gameNum) {
        websockets.put(getWsSocketKey(userId), webSocket);
//        webSocket.setUserid(userId);
//        String oldAddr = CacheUtils.getOnlineById(userId);
//        System.out.println("oldAddr:" + oldAddr);
//        if (oldAddr != null && !oldAddr.equals(YmlConfig.getGameServerStr(gameId,gameNum))) {
//            try {
//                JSONObject json = new JSONObject();
//                json.put("userId", userId);
//                DataPacket response = new DataPacket(BaseCommand.FOUCED_OFFLINE_REQ, json);
//                WsSyncClient.sendMsgToOther(response, oldAddr);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        CacheUtils.putUserOnline(userId,YmlConfig.getGameServerStr(gameId,gameNum));
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
//        int userId = webSocket.getUserId();
//        if (userId > 0) {
////            websockets.remove(getWsSocketKey(userId));
//            //CacheUtils.delUserOnline(userId);
//            //Offline.getInstance().process(webSocket);
//        }
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

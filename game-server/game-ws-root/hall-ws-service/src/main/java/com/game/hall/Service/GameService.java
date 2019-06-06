package com.game.hall.service;

import com.alibaba.fastjson.JSONObject;
import com.game.core.ws.server.manager.WebSocket;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:47 2019/5/28 0028
 * @explain :
 */
public interface GameService {
    
    /**
     * @Author: wx
     * @Date  : 下午 7:55 2019/5/28 0028 
     * @params: 
     * @Desc  :  gateWay登录初始化数据
     */
     void  gateWayJoin(WebSocket webSocket, JSONObject jsonObject);

     /**
      * @Author: wx
      * @Date  : 上午 9:40 2019/5/29 0029 
      * @params: 
      * @Desc  :  客户端的加入
      */
     String clientJoin();

     /**
      * @Author: wx
      * @Date  : 下午 7:51 2019/6/5 0005 
      * @params: 
      * @Desc  : 验证游戏名
      */
     void addauAccount();
}

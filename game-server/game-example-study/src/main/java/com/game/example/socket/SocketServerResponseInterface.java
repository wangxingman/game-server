package com.game.example.socket;

/**
 * @Author: wx
 * @Date: 下午 4:19 2019/10/25 0025
 * @Desc:
 * @version:
 */
public interface SocketServerResponseInterface {

    /**
     * 客户端断线回调
     */
    void clientOffline();

    /**
     * 客户端上线回调
     *
     * @param clientIp
     */
    void clientOnline(String clientIp);
}

package com.game.example.socket;

/**
 * @Author: wx
 * @Date: 下午 4:09 2019/10/25 0025
 * @Desc:
 * @version:
 */
public interface SocketClientResponseInterface<T> {


    /**
     * 客户端连接回调
     */
    void onSocketConnect();

    /**
     * 客户端收到服务端消息回调
     *
     * @param socketResult
     * @param code
     */
    void onSocketReceive(T socketResult, int code);

    /**
     * 客户端关闭回调
     *
     * @param msg
     * @param code
     */
    void onSocketDisable(String msg, int code);
}

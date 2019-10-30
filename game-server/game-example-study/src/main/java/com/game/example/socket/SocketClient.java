package com.game.example.socket;

import org.apache.http.util.TextUtils;

/**
 * @Author: wx
 * @Date: 下午 4:20 2019/10/25 0025
 * @Desc:
 * @version: 启动客户端
 */
@Deprecated
public class SocketClient implements SocketClientResponseInterface {

    private SocketClientThread socketClientThread;

    public SocketClient() {
        socketClientThread = new SocketClientThread("socketClientThread", this);
        new Thread(socketClientThread).start();
    }

    @Override
    public void onSocketConnect() {
//        Log.i(TAG, "onSocketConnect: 连接成功");
    }

    @Override
    public void onSocketReceive(Object socketResult, int code) {
//        Log.i(TAG, "onSocketReceive: 收到消息 ,  data: " + socketResult + " , code: " + code);
    }

    @Override
    public void onSocketDisable(String msg, int code) {
//        Log.i(TAG, "onSocketDisable: 连接断开 , msg: " + msg + " , code: " + code);
    }

    public <T> void sendData(T data) {
        //convert to string or serialize object
        String s = (String) data;
        if (TextUtils.isEmpty(s)) {
//            Log.i(TAG, "sendData: 消息不能为空");
            return;
        }
        if (socketClientThread != null) {
            socketClientThread.addRequest(s);
        }
    }

    public void stopSocket() {
        //一定要在子线程内执行关闭socket等IO操作
        new Thread(() -> {
            socketClientThread.setReConnect(false);
            socketClientThread.stopThread();
        }).start();
    }
}

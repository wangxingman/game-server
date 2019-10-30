package com.game.example.socket;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import rx.internal.schedulers.CachedThreadScheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: wx
 * @Date: 下午 4:17 2019/10/25 0025
 * @Desc:
 * @version:
 */
@Deprecated
public class Main {
    private static boolean isStart = true;
    private static ServerResponseThread serverResponseThread;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();

        System.out.println("服务端 " + SocketUtil.getIP() + " 运行中...\n");
        try {
            serverSocket = new ServerSocket(SocketUtil.PORT);
            while (isStart) {
                Socket socket = serverSocket.accept();
                //设定输入流读取阻塞超时时间(10秒收不到客户端消息判定断线)
                socket.setSoTimeout(10000);
                
                //给一个接口 的 实现里面的方法
                serverResponseThread = new ServerResponseThread(socket,
                        new SocketServerResponseInterface() {

                            @Override
                            public void clientOffline() {// 对方不在线
                                System.out.println("offline");
                            }

                            @Override
                            public void clientOnline(String clientIp) {
                                System.out.println(clientIp + " is online");
                                System.out.println("-----------------------------------------");
                            }
                        });

                if (socket.isConnected()) {
                    poolTaskExecutor.execute(serverResponseThread);
                }
            }

            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    isStart = false;
                    serverSocket.close();
                    if (serverSocket != null)
                        serverResponseThread.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

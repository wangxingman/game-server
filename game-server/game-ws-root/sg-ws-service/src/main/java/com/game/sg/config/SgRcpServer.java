package com.game.sg.config;

import com.codingapi.txlcn.txmsg.RpcServerInitializer;
import com.codingapi.txlcn.txmsg.dto.ManagerProperties;
import com.game.core.ws.initializer.WsInitializer;
import com.game.sg.config.properties.SgManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 1:05 2019/5/17 0017
 * @explain :
 */
public class SgRcpServer implements WsInitializer {

    /**
     * 配置txManager的配置信息
     */
    private final SgManagerConfig sgManagerConfig;

    private final ServerInitializer serverInitializer;

    @Autowired
    public SgRcpServer(SgManagerConfig sgManagerConfig, ServerInitializer serverInitializer) {
        this.sgManagerConfig = sgManagerConfig;
        this.serverInitializer = serverInitializer;
    }

    /**
     * @Author: wx
     * @Date  : 下午 1:05 2019/5/17 0017 
     * @params: 
     * @Desc  : 初始化服务器
     */
    @Override
    public void init() throws Exception {
        // 2. 初始化RPC Server
        ManagerProperties managerProperties = new ManagerProperties();
        managerProperties.setCheckTime(sgManagerConfig.getHeartTime());
        managerProperties.setRpcPort(sgManagerConfig.getPort());
        managerProperties.setRpcHost(sgManagerConfig.getHost());

        //各种配置信息传入
        serverInitializer.init(managerProperties);
    }
}

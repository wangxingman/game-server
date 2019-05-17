package com.game.sg.config;

import com.codingapi.txlcn.txmsg.dto.ManagerProperties;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 1:10 2019/5/17 0017
 * @explain :服务器创建服务 客户端还需要建立连接
 */
public interface ServerInitializer {

    /**
     * support server init
     *
     * @param managerProperties   配置信息
     */
    void init(ManagerProperties managerProperties);
}                                                                       

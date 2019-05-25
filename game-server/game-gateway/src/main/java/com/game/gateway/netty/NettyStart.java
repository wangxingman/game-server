package com.game.gateway.netty;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:43 2019/5/21 0021
 * @explain :
 */
public class NettyStart {
    
    private LocalNetService localNetService;

    public NettyStart(LocalNetService localNetService) {
        this.localNetService = localNetService;
        localNetService.startup();
    }
}

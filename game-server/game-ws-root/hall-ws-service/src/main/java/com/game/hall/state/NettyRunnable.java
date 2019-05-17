package com.game.hall.state;

import com.game.core.thread.AbstractRunnable;
import com.game.hall.netty.InitDispatcher;
import com.game.hall.po.MessageType;
import lombok.Data;

/**
 * @auther : wx
 * @Desc :
 * @Date :  下午 4:11 2019/5/17 0017
 * @explain :
 */
@Data
public class NettyRunnable extends AbstractRunnable {

    private MessageType  netMessageHead;
    
    /**
     * @Author: wx
     * @Date  : 下午 4:13 2019/5/17 0017 
     * @params: 
     * @Desc  : 构造方法执行线程方法
     */
    public NettyRunnable() {
        //初始化消息类型
        //todo 后期数字优化
         netMessageHead
                = MessageType.builder().serial(5).cmd((short)2).version((byte)3).build();
        this.startThread();
    }

    @Override
    public void run() {
        new InitDispatcher().init();
    }
}

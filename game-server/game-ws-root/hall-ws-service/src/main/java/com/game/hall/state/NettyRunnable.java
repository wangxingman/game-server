package com.game.hall.state;

import com.game.core.thread.AbstractRunnable;
import com.game.hall.common.BaseCommand;
import com.game.hall.netty.InitDispatcher;
import com.game.hall.po.MessageType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther : wx
 * @Desc :
 * @Date :  下午 4:11 2019/5/17 0017
 * @explain :
 */
@Slf4j
@Data
public class NettyRunnable extends AbstractRunnable {

    private Map<Short,MessageType> map = new ConcurrentHashMap();
    
    /**
     * @Author: wx
     * @Date  : 下午 4:13 2019/5/17 0017 
     * @params: 
     * @Desc  : 构造方法执行线程方法
     */
    public NettyRunnable() {
        //初始化消息类型 【怎么样才能 把对应的标识放进去】
        /**【这种写法肯定不好】*/
        //todo 后期优化
        MessageType netMessageHead
                = MessageType.builder().serial(5).version((byte)3).cmd(BaseCommand.hall.JOIN_HALL).build();
        map.put(BaseCommand.hall.JOIN_HALL,netMessageHead);
        this.startThread();
    }

    @Override
    public void run() {
        new InitDispatcher().init();
    }
}

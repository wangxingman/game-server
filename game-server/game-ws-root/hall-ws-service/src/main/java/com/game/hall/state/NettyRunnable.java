package com.game.hall.state;

import com.game.common.Const.Const;
import com.game.core.thread.AbstractRunnable;
import com.game.core.ws.dto.AbsMessageType;
import com.game.hall.ws.InitDispatcher;
import com.game.core.ws.dto.MessageType;
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

    /**
     * @Author: wx
     * @Date  : 下午 4:13 2019/5/17 0017 
     * @params: 
     * @Desc  : 构造方法执行线程方法
     */
    public NettyRunnable() {
        identification = System.nanoTime();
        //初始化消息类型 【怎么样才能 把对应的标识放进去】
        /**【这种写法肯定不好】*/
        AbsMessageType absMessageType
                = AbsMessageType.builder().serial(Const.number.FIVE).version((byte)Const.number.THREE).build();

        map.put(identification,MessageType.builder().absMessageType(absMessageType).build());
        this.startThread();
    }

    @Override
    public void run() {
        new InitDispatcher().init();
    }

}

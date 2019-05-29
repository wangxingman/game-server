package com.game.hall.ws;

import com.game.common.Const.Const;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.server.BaseDispatcher;
import com.game.hall.hanlder.JoinHallHanlder;
import com.game.hall.state.NettyRunnable;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:18 2019/5/17 0017
 * @explain : 加入房间的验证体 还是需要封装一下 不然不安全
 */
public class InitDispatcher implements BaseDispatcher {
    
    MessageType messageType = NettyRunnable.getMessageType();

    @Override
    public void init() {
        messageType.setCmd(Const.hall.JOIN_HALL);
        BaseDispatcher.register_copy(messageType ,new JoinHallHanlder());
        
    }
}

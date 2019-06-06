package com.game.hall.ws;

import com.game.common.Const.Const;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.server.BaseDispatcher;
import com.game.hall.hanlder.JoinHallHanlder;
import com.game.hall.hanlder.NameHallHanlder;
import com.game.hall.state.NettyRunnable;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:18 2019/5/17 0017
 * @explain : 加入房间的验证体 还是需要封装一下 不然不安全
 */
@Slf4j
public class InitDispatcher implements BaseDispatcher {



    @Override
    public void init() {
        /**网关加入大厅验证*/
        BaseDispatcher.register_copy(new MessageType(Const.hall.JOIN_HALL) ,new JoinHallHanlder());
        /**客户端 名字的操作*/
        BaseDispatcher.register_copy(new MessageType(Const.hall.NAME_HALL)  ,new NameHallHanlder());

        log.info(String.valueOf(BaseDispatcher.localMap));
    }
}

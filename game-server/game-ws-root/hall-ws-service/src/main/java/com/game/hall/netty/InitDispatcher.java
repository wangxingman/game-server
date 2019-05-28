package com.game.hall.netty;

import com.game.hall.hanlder.JoinHallHanlder;
import com.game.hall.state.NettyRunnable;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:18 2019/5/17 0017
 * @explain : 加入房间的验证体 还是需要封装一下 不然不安全
 */
public class InitDispatcher implements BaseDispatcher{

    @Override
    public void init() {
        BaseDispatcher.register_copy( new NettyRunnable().getMap().get(BaseCommand.hall.JOIN_HALL),new JoinHallHanlder());
    }
}

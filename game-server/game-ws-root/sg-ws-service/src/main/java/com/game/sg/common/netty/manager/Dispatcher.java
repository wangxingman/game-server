package com.game.sg.common.netty.manager;

import java.util.HashMap;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:51 2019/5/5 0005
 */
public class Dispatcher {

    public static HashMap<Integer, Handler> localMap = new HashMap<Integer, Handler>();
    
    public static Handler getDispatch(Integer command) {
        return localMap.get(command);
    }
}
